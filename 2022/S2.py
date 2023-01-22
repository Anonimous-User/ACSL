#!/bin/python3

import math
import os
import random
import re
import sys



#
# Complete the 'findLastOctal' function below.
#
# The function is expected to return an INTEGER.
# The function accepts STRING s as parameter.
#

def findLastOctal(s):
    bins = ""
    for chars in s:
        asc=bin(ord(chars))
        asc=asc[0]+asc[2:]
        if(len(asc)==7):
            asc="0"+asc
        bins+=asc
    
    checker=-1
    flag = True
    while(flag):
        checker+=1
        asc=bin(checker)
        binchecker=asc[2:]
        flag = False
        
        i=0
        flagg = True
        lenchecker = len(binchecker)
        while i<len(bins) and flagg:
            if bins[i:i+lenchecker]==binchecker:
                flagg = False
                flag = True
                bins=bins[:i]+bins[i+lenchecker:]
            i+=1
        
        i=len(bins)-1
        flagg = True
        while i>=0 and flagg:
            if bins[i:i+lenchecker]==binchecker:
                flagg = False
                flag = True
                bins=bins[:i]+bins[i+lenchecker:]
            i-=1



    octs = oct(int(bins, 2))
    octs = octs[2:]
    if(not octs.__contains__("0")):
        return -1
    checker=-1
    flag=True
    while(flag):
        checker+=1
        asc=oct(checker)
        octchecker=asc[2:]
        flag=False
        
        i=0
        flagg=True
        lenchecker = len(octchecker)
        while(i<len(octs) and flagg):
            if(octs[i:i+lenchecker]==octchecker):
                flagg=False
                flag=True
                octs=octs[:i]+octs[i+lenchecker:]
            i+=1
        
        i=len(octs)-1
        flagg = True
        while i>=0 and flagg:
            if octs[i:i+lenchecker]==octchecker:
                flagg = False
                flag = True
                octs=octs[:i]+octs[i+lenchecker:]
            i-=1
    
    return checker-1

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s = input()

    result = findLastOctal(s)

    fptr.write(str(result) + '\n')

    fptr.close()