class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aa","a*"));
    }
    public boolean isMatch(String s, String p) {
        boolean matches[][] = new boolean[s.length()+1][p.length()+1];
        matches[s.length()][p.length()] = true;

        for(int i = s.length(); i >= 0; i--) {
            for(int j = p.length()-1; j >= 0; j--){ // j = p.length()-1 ，是因为 matches[s.length()][p.length()] = true, 此时是用 p的最后一个字符来匹配空串
                boolean m = i < s.length() && (p.charAt(j) == '.'|| s.charAt(i) == p.charAt(j) );

                if(j < p.length()-1 && p.charAt(j+1) == '*'){
                    matches[i][j] = matches[i][j+2] || (m && matches[i+1][j]);
                } else {
                    matches[i][j] = m && matches[i+1][j+1];
                }

            }
        }
        return matches[0][0];
    }
}