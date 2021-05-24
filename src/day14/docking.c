#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#ifdef Z80
#pragma printf = "%s %lld %d"
#endif

#define long uint64_t
#define int int32_t

char* input[600];
int length = 0;

long mask0 = 0;
long mask1 = 0;
long maskX = 0;

long memAddr[512];
long memMask[512];
int memCount;

long result;

int bitCount(long l) {
    int result = 0;
    while (l != 0) {
        if ((l & 1) == 1) {
            result++;
        }
        l = l >> 1;
    }
    return result;
}

long getBits(char* mask, char c) {
    long result = 0;

    for (int i = 0; i < strlen(mask); i++) {
        result = result << 1;
        if (mask[i] == c) {
            result = result | 1;
        }
    }

    return result;
}

static long applyBits(long mask, long bits) {
    long result = 0;
    int position = 0;

    while (mask != 0) {
        if ((mask & 1) == 1) {
            result = result | ((bits & 1) << position);
            bits = bits >> 1;
        }

        position++;
        mask = mask >> 1;
    }

    return result;
}

void poke0(long address, long value) {
    for (int i = 0; i < memCount; i++) {
        if ((address & memMask[i]) == (memAddr[i])) {
             return;
        }
    }

    result += value;
}

void poke1(long address, long value) {
    poke0(address, value & ~mask0 | mask1);

    memAddr[memCount] = address;
    memMask[memCount] = ~0;
    memCount++;
}

void poke2(long address, long value) {
    address = address & mask0 | mask1;

    int combs = 1 << bitCount(maskX);
    for (int i = 0; i < combs; i++) {
        long realAddress = address | applyBits(maskX, i); 
        poke0(realAddress, value);
    }

    memAddr[memCount] = address & ~maskX;
    memMask[memCount] = ~maskX;
    memCount++;
}

void load() {
    FILE *fp;
    char str[128];
    char* filename = "input.txt";
 
    fp = fopen(filename, "r");
    if (fp == NULL){
        printf("Could not open file %s",filename);
        exit(1);
    }
    while (fgets(str, 128, fp) != NULL) {
        int l = strlen(str);
        if (str[l-1] < 32) {
            str[l-1] = 0;
            l--;
        }
        input[length] = malloc(l+1);
        strcpy(input[length], str);
        length++;
    }
    fclose(fp);

    for (int i = 1; i < length; i++) {
        if (strncmp(input[i], "mask", 4) != 0) {
            char* s = input[i - 1];
            input[i - 1] = input[i];
            input[i] = s;
        }
    }
}

void process(int part) {
    printf("\n--- Part %ld ---\n\n", part);

    memCount = 0;
    result = 0;

    for (int i = length - 1; i >= 0; i--) {
        char* s = input[i];
        if (strncmp(s, "mask", 4) == 0) {
            char* t = s+7;

            mask0 = getBits(t, '0');
            mask1 = getBits(t, '1');
            maskX = getBits(t, 'X');
        } else if (strncmp(s, "mem", 3) == 0) {
            char* p = strchr(s, ']');
            char buf[10];

            memset(buf, 0, 10);
            strncpy(buf,s+4,(int)(p-(s+4)));

            long address = atol(buf);
            long value = atol(p+4);

            if (part == 1) {
                poke1(address, value);
            } else {
                poke2(address, value);
            }
            putchar('.');
        }
    }

    printf("\n\nResult=%lld\n", result);
}

int main(void) {
    puts("* AoC 2020.14 Docking Data *");
    load();
    process(1);
    process(2);
}
