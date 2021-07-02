/**
 * Operation Order parts 1 and 2. Old-school solution using recursive descent
 * parsing and on-the-fly processing.
 *
 * Compile for CP/M target using
 *
 *   zcc +cpm -v -Cz"--clean" -create-app order.c -o order.com
 *
 * or for native Next target using
 *
 *   zcc +zxn -v -clib=classic -lesxdos -Cz"--clean" -subtype=tap -create-app order.c -o order.tap
 *
 * The following options might result in faster code being generated:
 *
 *   -compiler=sdcc -SO3 --max-allocs-per-node200000
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#ifdef Z80
#pragma printf = "%s %llu %d"
#endif

int rules;          // 0 = normal math, 1 = no precedences, 2 = '+' over '*'

char input[256];    // Expression to parse / evaluate
int position;       // Next character to read
char token;         // Last token scanned (first character only)
uint64_t value;     // Value of last numeric token scanned

uint64_t evalExpr();

/**
 * Over and out.
 */
void error(char *msg) {
    puts(msg);
    exit(1);
}

/**
 * Implements the scanner.
 */
void nextToken() {
    char c = input[position];
    while (c == ' ') {
        c = input[++position];
    }

    token = c;

    //putchar(token);

    if (isdigit(c)) {
        value = 0;
        while (isdigit(c)) {
            value = value * 10 + (c - '0');
            c = input[++position];
        }
    } else {
        position++;
    }
}

/**
 * Factor rule.
 *
 * fact -> '(' expr ')' | number        for normal math and both parts.
 */
uint64_t evalFact() {
    uint64_t result;

    if (token == '(') {
        nextToken();
        result = evalExpr();
        if (token != ')') {
            error("Parse error, ')' expected.");
        }
        nextToken();
    } else {
        result = value;
        nextToken();
    }

    return result;
}

/**
 * Term rule.
 * 
 * term -> fact { '*' fact }            for normal math.
 * term -> fact { '+' fact }            for part 2.
 *
 * Unused in part 1.
 */
uint64_t evalTerm() {
    uint64_t result = evalFact();

    if (rules == 0) {
        while (token == '*') {
            nextToken();
            result = result * evalFact();
        }
    } else {
        while (token == '+') {
            nextToken();
            result = result + evalFact();
        }
    }

    return result;
}

/**
 * Expression rule.
 * 
 * expr -> term { '+' term }            for normal math.
 * expr -> term { ( '+' | '*' ) term }  for part 1.
 * expr -> term { '*' term }            for part 2.
 */
uint64_t evalExpr() {
    uint64_t result;

    if (rules == 0) {
        result = evalTerm();

        while (token == '+') {
            nextToken();
            result = result + evalTerm();
        }
    } else if (rules == 1) {
        result = evalFact();

        while (token == '+' || token == '*') {
            if (token == '+') {
                nextToken();
                result = result + evalFact();
            } else {
                nextToken();
                result = result * evalFact();
            }
        }
    } else {
        result = evalTerm();

        while (token == '*') {
            nextToken();
            result = result * evalTerm();
        }
    }

    return result;
}

/**
 * Parser and evaluator entry point.
 */
uint64_t eval() {
    position = 0;
    nextToken();

    uint64_t result = evalExpr();

    if (token >= ' ') {
        error("Parse error, EOL expected.");
    }

    return result;
}

/**
 * Reads input.txt and processes it according to the rules of the given part.
 */
void process(int part) {
    printf("\n--- Part %d ---\n\n", part);

    rules = part;

    uint64_t total = 0;

    FILE *fp;
    char* filename = "input.txt";
 
    fp = fopen(filename, "r");
    if (fp == NULL){
        printf("Could not open file %s",filename);
        exit(1);
    }
    while (fgets(input, 256, fp) != NULL) {
        input[strlen(input)-1] = 0;
        uint64_t result = eval();
        putchar('.'); //printf("%s = %llu\n", input, result);
        total = total + result;
    }
    fclose(fp);

    printf("\n\nGrand total = %llu\n", total);
}

int main(void) {
    puts("* AoC 2020.18 Operation Order *");

    process(1);
    process(2);
}
