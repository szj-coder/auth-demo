## Antlr4

- 一些定义
AST：抽象语法树（Abstract Syntax Tree）

涉及算法：**正则表达式（Regular Expression）**和**有限自动机(用于词法解析)**

在antlr4的g4文件中，大写开头表示定义词法，小写开头定义是语法

每个g4文件中都需要包含 grammar 后面跟文件名称，默认使用左递归原则，也可自定义为有递归

- 参考
>  [antlr4官方文档](https://github.com/antlr/antlr4/blob/4.6/doc/index.md)

>  [编译原理之美](https://time.geekbang.org/column/article/118132)

### 词法分析（Lexical Analysis）

```c
#include <stdio.h>
int main(int argc, char* argv[]){
    int age = 45;
    if (age >= 17+8+20) {
        printf("Hello old man!\\n");
    }
    else{
        printf("Hello young man!\\n");
    }
    return 0;
}
```
if、else、int 这样的关键字，main、printf、age 这样的标识符，+、-、= 这样的操作符号，还有花括号、圆括号、分号这样的符号，以及数字字面量、字符串字面量等。这些都是**词法记号**，也可以称作**Token**。

词法分析可以依靠**有限自动机**来进行*分词*

需要注意点
- 词法分析优先级
- 要尽量避免词法定义出现歧义

### 语法分析（Syntactic Analysis, or Parsing）

词法分析是识别一个个的单词，而语法分析就是在词法分析的基础上识别出程序的语法结构。这个结构是一个树状结构，是计算机容易理解和执行的。

#### 表达式


### 执行顺序

### antlr4-parse工具使用指南

常用指令
```shell
# 生成解析树 输入指令后回车输入表达式，win按ctrl+z结束，unix按ctrl+d结束
antlr4-parse.exe .\AntlrDemo.g4 factor -tree
# 生成解析树图 更加直观
antlr4-parse.exe .\AntlrDemo.g4 script -gui
```