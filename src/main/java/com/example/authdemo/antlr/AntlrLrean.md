## Antlr4

- 一些定义
  AST：抽象语法树（Abstract Syntax Tree）

涉及算法：**正则表达式（Regular Expression）**和**有限自动机(用于词法解析)**

在antlr4的g4文件中，大写开头表示定义词法，小写开头定义是语法

每个g4文件中都需要包含 grammar 后面跟文件名称，默认使用左递归原则，也可自定义为有递归

- 参考

> [antlr4官方文档](https://github.com/antlr/antlr4/blob/4.6/doc/index.md)

> [编译原理之美](https://time.geekbang.org/column/article/118132)

> [ANTLR 4简明教程](https://www.cntofu.com/book/115/basic-concept.md)

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

if、else、int 这样的关键字，main、printf、age 这样的标识符，+、-、= 这样的操作符号，还有花括号、圆括号、分号这样的符号，以及数字字面量、字符串字面量等。这些都是
**词法记号**，也可以称作**Token**。

词法分析可以依靠**有限自动机**来进行*分词*

需要注意点

- 词法分析优先级
- 要尽量避免词法定义出现歧义

> 5.5.1

fragment: 表示下面的生命不是词法符号，而是被其他词法规则使用，类似：

```antlrv4
FLOAT: DIGIT+ '.' DIGIT*
     |        '.' DIGIT+
     ;
fragment
DIGIT: [0-9]
```

**匹配一个字符串**

```antlrv4
STRING : '"' .*? '"'';
fragment
ESC : '\\"' | '\\\\'; // 匹配双字符序列 \" 和 \\
```

- '.': 匹配任意字符
- '*': 匹配零个或者一个
- '?': 非贪婪

**匹配注释和空白符**

```antlrv4
WS: [ \t\n\r]+ -> skip
```

### 语法分析（Syntactic Analysis, or Parsing）

词法分析是识别一个个的单词，而语法分析就是在词法分析的基础上识别出程序的语法结构。这个结构是一个树状结构，是计算机容易理解和执行的。

### 一些语法特性

#### 孤岛语法

`mode <name>`

#### 重写输入流

`TokenStreamRewriter.java`

#### 将词法符号送入不同的通道

```antlrv4
COMMENT 
    : '/*' .*? '*/' -> channel(HIDDEN) // 匹配 /*和 */之间的任何东西
    ;
WS  : [ \r\t\u00C\n]+ -> channel(HIDDEN)
```

### 匹配顺序

- antlr会自动将此法规则配置在文法规则之后
- 按照定义顺序决定优先级,匹配词法或者语法规则
- 解析顺序：

#### 二义性

antlr4根据定义的先后顺序来解决二义性，先定义的优先级高

### antlr4-parse工具使用指南

常用指令

```shell
# 生成解析树 输入指令后回车输入表达式，win按ctrl+z结束，unix按ctrl+d结束
antlr4-parse.exe .\AntlrDemo.g4 factor -tree
# 生成解析树图 更加直观
antlr4-parse.exe .\AntlrDemo.g4 script -gui
```