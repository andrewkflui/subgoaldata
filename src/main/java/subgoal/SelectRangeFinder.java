package subgoal;
/***************************************************************
 *
 * Subgoal Data Preparation
 * Written by Dr. Andrew Kwok-Fai LUI
 * On 28/2/2020
 *
 * Copyright Andrew Kwok-Fai LUI 2020
 *
 */
public class SelectRangeFinder {

    private int startrow = -1;
    private int endrow = -1;
    private int startcol = -1;
    private int endcol = -1;

    public SelectRangeFinder(String text, int start, int end) {
        int row = 0;
        int col = 0;
        int len = text.length();
        for (int i = 0; i < len; i++) {
            if (i == end) {
                endrow = row;
                endcol = col;
                return;
            }
            if (text.charAt(i) == '\n') {
                row++;
                col = -1;
            }
            if (i == start) {
                startrow = row;
                startcol = col;
            }
            col++;
        }
        if (end == len) {
            endrow = row;
            endcol = col;
        }
    }
    
    public boolean isValid() {
      return startrow != -1 && endrow != -1 && startcol != -1 && endcol != -1;
    }
    
    public boolean isSameRow() {
      return startrow == endrow && startrow != -1;
    }

    public int getStartrow() {
        return startrow;
    }

    public int getEndrow() {
        return endrow;
    }

    public int getStartcol() {
        return startcol;
    }

    public int getEndcol() {
        return endcol;
    }

    public String toString() {
        return startrow + "," + startcol + " to " + endrow + "," + endcol;
    }

}
