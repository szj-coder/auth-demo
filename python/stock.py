#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
A股股价涨幅计算程序 - 动态列处理版本
支持任意天数的涨幅计算，智能跳过已有数据和未来日期
"""

import random
import re
import sys
import time
import warnings
from datetime import datetime, timedelta

import akshare as ak
import numpy as np
import pandas as pd

warnings.filterwarnings('ignore')

# 检查Python版本
if sys.version_info < (3, 6):
    print("此程序需要Python 3.6或更高版本")
    sys.exit(1)


class StockPriceCalculator:
    def __init__(self, excel_file_path):
        """
        初始化股价计算器

        Args:
            excel_file_path (str): Excel文件路径
        """
        self.excel_file_path = excel_file_path
        self.df = None
        self.today = datetime.now().strftime('%Y-%m-%d')

    def read_excel(self):
        """读取Excel文件并分析列结构"""
        try:
            # 使用openpyxl引擎确保兼容性
            self.df = pd.read_excel(self.excel_file_path, engine='openpyxl')
            print(f"成功读取Excel文件，共有{len(self.df)}条记录")
            print("Excel文件列名:", self.df.columns.tolist())

            # 分析列结构
            self._analyze_columns()

            print("\n前3行数据预览:")
            print(self.df.head(3).to_string())
            return True
        except FileNotFoundError:
            print(f"错误：找不到文件 {self.excel_file_path}")
            return False
        except Exception as e:
            print(f"读取Excel文件失败: {e}")
            print("请确保文件格式正确，且安装了openpyxl: pip3 install openpyxl")
            return False

    def _analyze_columns(self):
        """分析Excel列结构，识别固定列和涨幅列"""
        self.fixed_columns = ['添加日期', '股票代码', '股票名称', '添加日期股价']
        self.return_columns = {}  # 存储涨幅列信息 {天数: 列名}

        # 检查固定列是否存在
        missing_fixed = [col for col in self.fixed_columns[:3] if col not in self.df.columns]
        if missing_fixed:
            print(f"警告：Excel文件缺少必要的列: {missing_fixed}")

        # 如果没有"添加日期股价"列，则创建
        if '添加日期股价' not in self.df.columns:
            self.df['添加日期股价'] = np.nan
            print("已创建'添加日期股价'列")

        # 识别涨幅列（格式：X日涨幅 或 X天涨幅）
        pattern = r'(\d+)[日天]涨幅'
        for col in self.df.columns:
            match = re.match(pattern, col)
            if match:
                days = int(match.group(1))
                self.return_columns[days] = col
                print(f"识别到涨幅列: {col} (对应{days}天)")

        if not self.return_columns:
            print("警告：未找到涨幅列，请确保列名格式为'X日涨幅'或'X天涨幅'")
        else:
            print(f"共识别到{len(self.return_columns)}个涨幅列")

    def convert_stock_code(self, stock_code):
        """
        将股票代码转换为akshare格式（6位数字）

        Args:
            stock_code (str): 原始股票代码

        Returns:
            str: akshare格式的股票代码
        """
        stock_code = str(stock_code).strip()
        # 移除可能的后缀（如.SS, .SZ）
        if '.' in stock_code:
            stock_code = stock_code.split('.')[0]
        # 确保是6位数字
        return stock_code.zfill(6)

    def calculate_target_date(self, base_date, days_offset):
        """
        计算目标日期（跳过周末）

        Args:
            base_date (str): 基准日期 (YYYY-MM-DD格式)
            days_offset (int): 偏移天数

        Returns:
            str: 目标日期 (YYYY-MM-DD格式)
        """
        base = datetime.strptime(base_date, '%Y-%m-%d')
        target = base

        days_added = 0
        while days_added < days_offset:
            target += timedelta(days=1)
            # 跳过周末（周六=5, 周日=6）
            if target.weekday() < 5:
                days_added += 1

        return target.strftime('%Y-%m-%d')

    def is_future_date(self, date_str):
        """
        判断日期是否为未来时间

        Args:
            date_str (str): 日期字符串

        Returns:
            bool: True表示是未来时间
        """
        return date_str > self.today

    def get_stock_price(self, stock_code, date, max_retries=3):
        """
        使用akshare获取指定日期的股票收盘价

        Args:
            stock_code (str): 股票代码
            date (str): 日期 (YYYY-MM-DD格式)
            max_retries (int): 最大重试次数

        Returns:
            float: 股票收盘价，获取失败返回None
        """
        for attempt in range(max_retries):
            try:
                # 添加延迟，避免请求过快
                if attempt > 0:
                    delay = random.uniform(1, 3)
                    print(f"      第{attempt + 1}次尝试，等待{delay:.1f}秒...")
                    time.sleep(delay)
                else:
                    time.sleep(random.uniform(0.2, 0.5))

                ak_code = self.convert_stock_code(stock_code)

                # 计算查询的起始和结束日期
                target_date = datetime.strptime(date, '%Y-%m-%d')
                start_date = (target_date - timedelta(days=10)).strftime('%Y%m%d')
                end_date = (target_date + timedelta(days=10)).strftime('%Y%m%d')

                # 使用akshare获取历史数据
                try:
                    # 获取个股历史数据
                    hist_data = ak.stock_zh_a_hist(
                        symbol=ak_code,
                        period="daily",
                        start_date=start_date,
                        end_date=end_date,
                        adjust=""
                    )
                except Exception:
                    # 如果上面的方法失败，尝试其他方法
                    hist_data = ak.stock_zh_a_daily(
                        symbol=f"sz{ak_code}" if ak_code.startswith(('00', '30')) else f"sh{ak_code}",
                        start_date=start_date,
                        end_date=end_date
                    )

                if hist_data is None or hist_data.empty:
                    if attempt < max_retries - 1:
                        continue
                    return None

                # 转换日期格式用于比较
                hist_data['日期'] = pd.to_datetime(hist_data['日期']).dt.strftime('%Y-%m-%d')

                # 查找目标日期的数据
                target_row = hist_data[hist_data['日期'] == date]

                if not target_row.empty:
                    # 找到确切日期的数据
                    close_price = target_row.iloc[0]['收盘']
                    return float(close_price)
                else:
                    # 找最接近的交易日
                    hist_data['日期_dt'] = pd.to_datetime(hist_data['日期'])
                    target_dt = pd.to_datetime(date)

                    # 计算日期差异
                    hist_data['date_diff'] = abs((hist_data['日期_dt'] - target_dt).dt.days)
                    closest_row = hist_data.loc[hist_data['date_diff'].idxmin()]

                    close_price = closest_row['收盘']
                    closest_date = closest_row['日期']

                    if abs((pd.to_datetime(closest_date) - target_dt).days) <= 5:
                        return float(close_price)
                    else:
                        return None

            except Exception as e:
                if attempt < max_retries - 1:
                    time.sleep(random.uniform(0.5, 1.5))
                    continue
                else:
                    return None

        return None

    def calculate_returns(self, batch_size=5):
        """
        计算股价涨幅（智能跳过已有数据和未来日期）

        Args:
            batch_size (int): 每批处理的股票数量
        """
        if self.df is None:
            print("请先读取Excel文件")
            return

        # 检查必要的列是否存在
        required_columns = ['添加日期', '股票代码', '股票名称']
        missing_columns = [col for col in required_columns if col not in self.df.columns]
        if missing_columns:
            print(f"错误：Excel文件缺少必要的列: {missing_columns}")
            return

        # 确保日期列是正确的格式
        try:
            self.df['添加日期'] = pd.to_datetime(self.df['添加日期'], errors='coerce').dt.strftime('%Y-%m-%d')
        except Exception as e:
            print(f"日期格式转换失败: {e}")
            return

        print(f"\n开始计算股价涨幅...（使用akshare数据源，每批{batch_size}只股票）")
        print(f"当前日期: {self.today}")

        successful_count = 0
        total_batches = (len(self.df) + batch_size - 1) // batch_size

        for batch_num in range(total_batches):
            start_idx = batch_num * batch_size
            end_idx = min((batch_num + 1) * batch_size, len(self.df))

            print(f"\n=== 处理第 {batch_num + 1}/{total_batches} 批 (第{start_idx + 1}-{end_idx}条记录) ===")

            update_count = 0
            for index in range(start_idx, end_idx):
                row = self.df.iloc[index]
                print(f"\n处理第 {index + 1}/{len(self.df)} 条记录: {row['股票名称']} ({row['股票代码']})")

                stock_code = str(row['股票代码']).strip()
                base_date = row['添加日期']

                df = ak.stock_info_a_code_name()
                stock_name = df.loc[df['code'] == self.convert_stock_code(stock_code), 'name'].values[0]
                self.df.at[index, '股票名称'] = stock_name
                self.df.at[index, '股票代码'] = self.convert_stock_code(stock_code)

                if pd.isna(base_date) or base_date == 'NaT':
                    print(f"  跳过：添加日期为空")
                    continue

                # 获取或计算添加日期股价
                base_price = self._get_or_calculate_base_price(index, stock_code, base_date)

                if base_price is None:
                    print(f"  跳过：无法获取添加日期股价")
                    continue

                update_count += 1
                successful_count += 1

                # 计算各个时间段的涨幅
                self._calculate_period_returns(index, stock_code, base_date, base_price)

            # 批次间等待
            # if batch_num < total_batches - 1 and update_count > 0:
            #     wait_time = random.uniform(1, 3)
            #     print(f"\n批次完成，更新{update_count}条记录，等待{wait_time:.1f}秒后处理下一批...")
            #     time.sleep(wait_time)

        print(f"\n计算完成！成功处理 {successful_count}/{len(self.df)} 条记录")

    def _get_or_calculate_base_price(self, index, stock_code, base_date):
        """
        获取或计算添加日期的股价

        Args:
            index (int): 行索引
            stock_code (str): 股票代码
            base_date (str): 添加日期

        Returns:
            float: 基准股价，失败返回None
        """
        # 检查是否已有添加日期股价
        existing_price = self.df.at[index, '添加日期股价']

        if not pd.isna(existing_price) and existing_price > 0:
            print(f"  ✓ 使用已有添加日期股价: {existing_price:.2f}")
            return float(existing_price)

        # 需要获取股价
        print(f"  获取 {base_date} 的股价...")
        base_price = self.get_stock_price(stock_code, base_date)

        if base_price is not None:
            self.df.at[index, '添加日期股价'] = base_price
            print(f"  ✓ 添加日期股价: {base_price:.2f}")
            return base_price

        return None

    def _calculate_period_returns(self, index, stock_code, base_date, base_price):
        """
        计算各个时间段的涨幅

        Args:
            index (int): 行索引
            stock_code (str): 股票代码
            base_date (str): 基准日期
            base_price (float): 基准股价
        """
        for days, col_name in self.return_columns.items():
            # 检查是否已有数据
            existing_return = self.df.at[index, col_name]

            if not pd.isna(existing_return):
                print(f"  ✓ {col_name}: {existing_return:.2f}% (已存在)")
                continue

            # 计算目标日期
            target_date = self.calculate_target_date(base_date, days)

            # 检查是否为未来日期
            if self.is_future_date(target_date):
                print(f"  ⏭ {col_name}: 跳过 (目标日期{target_date}为未来时间)")
                continue

            # 获取目标日期的股价
            print(f"    获取 {target_date} ({days}个交易日后) 的股价...")
            target_price = self.get_stock_price(stock_code, target_date)

            if target_price is not None:
                # 计算涨幅
                return_rate = ((target_price - base_price) / base_price) * 100
                self.df.at[index, col_name] = round(return_rate, 2)
                print(f"  ✓ {col_name}: {return_rate:.2f}% (从{base_price:.2f}到{target_price:.2f})")
            else:
                print(f"  ✗ {col_name}: 无法获取目标日期股价")

    def save_results(self, output_file):
        """
        保存计算结果到新的Excel文件

        Args:
            output_file (str): 输出文件路径
        """
        if self.df is None:
            print("没有数据需要保存")
            return

        try:
            # 使用openpyxl引擎保存，确保格式兼容
            with pd.ExcelWriter(output_file, engine='openpyxl') as writer:
                self.df.to_excel(writer, index=False, sheet_name='股票涨幅计算结果')
            print(f"\n✓ 结果已成功保存到: {output_file}")
            print(f"共保存 {len(self.df)} 条记录")
        except Exception as e:
            print(f"保存文件失败: {e}")
            print("请确保文件未被其他程序打开，且有写入权限")

    def display_summary(self):
        """显示计算结果摘要"""
        if self.df is None:
            print("没有数据")
            return

        print("\n=== 计算结果摘要 ===")

        # 统计各列的完成情况
        print(f"\n数据完成情况统计:")
        print(f"总记录数: {len(self.df)}")

        # 添加日期股价完成情况
        base_price_count = self.df['添加日期股价'].notna().sum()
        print(f"添加日期股价: {base_price_count}/{len(self.df)} ({base_price_count / len(self.df) * 100:.1f}%)")

        # 各涨幅列完成情况
        for days, col_name in sorted(self.return_columns.items()):
            count = self.df[col_name].notna().sum()
            print(f"{col_name}: {count}/{len(self.df)} ({count / len(self.df) * 100:.1f}%)")

        # 显示部分详细结果
        successful_records = self.df[~self.df['添加日期股价'].isna()].head(5)

        if not successful_records.empty:
            print(f"\n前{len(successful_records)}条记录详情:")
            for index, row in successful_records.iterrows():
                print(f"\n{row['股票名称']} ({row['股票代码']}):")
                print(f"  添加日期: {row['添加日期']}")
                if not pd.isna(row['添加日期股价']):
                    print(f"  添加日期股价: {row['添加日期股价']:.2f}")

                    for days, col_name in sorted(self.return_columns.items()):
                        if not pd.isna(row[col_name]):
                            print(f"  {col_name}: {row[col_name]:+.2f}%")

    def test_akshare_connection(self):
        """测试akshare连接"""
        print("测试akshare数据连接...")
        try:
            # 测试获取一只知名股票的数据
            test_data = ak.stock_zh_a_hist(symbol="000001", period="daily", start_date="20240101", end_date="20240110",
                                           adjust="")
            if not test_data.empty:
                print("✓ akshare连接正常")
                return True
            else:
                print("⚠ akshare连接异常：返回数据为空")
                return False
        except Exception as e:
            print(f"✗ akshare连接失败: {e}")
            print("请检查网络连接或尝试更新akshare: pip3 install --upgrade akshare")
            return False


def main():
    """主函数"""
    print("=== A股股价涨幅智能计算程序 ===")
    print("支持功能：")
    print("- 动态识别任意天数的涨幅列（格式：X日涨幅 或 X天涨幅）")
    print("- 智能跳过已有数据")
    print("- 自动跳过未来日期")
    print("- 只计算缺失的数据")
    print(f"Python版本: {sys.version}")

    # 设置文件路径
    input_file = "股票涨幅计算结果.xlsx"  # 请修改为你的Excel文件路径
    output_file = "股票涨幅计算结果.xlsx"

    print(f"\n输入文件: {input_file}")
    print(f"输出文件: {output_file}")

    # 创建计算器实例
    calculator = StockPriceCalculator(input_file)

    # 测试akshare连接
    if not calculator.test_akshare_connection():
        print("\n程序退出")
        return

    # 读取Excel文件
    if not calculator.read_excel():
        print("\n程序退出")
        return

    # 询问用户批次大小
    try:
        # batch_size = input(f"\n请输入每批处理的股票数量 (默认5，回车确认): ").strip()
        batch_size = 5
        batch_size = int(batch_size) if batch_size else 5
        if batch_size <= 0:
            batch_size = 5
    except ValueError:
        batch_size = 5

    print(f"将使用批次大小: {batch_size}")

    # 计算股价涨幅
    calculator.calculate_returns(batch_size=batch_size)

    # 显示计算结果摘要
    calculator.display_summary()

    # 保存结果
    calculator.save_results(output_file)

    print("\n=== 程序运行完成 ===")
    print("\n使用说明：")
    print("1. Excel文件前4列固定：添加日期、股票代码、股票名称、添加日期股价")
    print("2. 后续列为涨幅列，格式：'X日涨幅'或'X天涨幅'（如：3日涨幅、5日涨幅）")
    print("3. 程序会自动跳过已有数据和未来日期，只计算缺失的数据")
    print("4. 可重复运行，增量计算新的数据")


if __name__ == "__main__":
    # Python3安装所需库的命令:
    # pip3 install pandas openpyxl akshare numpy

    main()
