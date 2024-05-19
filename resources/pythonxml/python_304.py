class Solution:
    def firstUniqChar(self, s: str) -> int:
        strDict = dict()
        for i in range(len(s)):
            if s[i] in strDict:
                strDict[s[i]] += 1
            else:
                strDict[s[i]] = 1

        for i in range(len(s)):
            if s[i] in strDict and strDict[s[i]] == 1:
                return i
        return -1