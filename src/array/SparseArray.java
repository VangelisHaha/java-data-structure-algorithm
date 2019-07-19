package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 稀疏数组
 *  稀疏数组为n行 3列
 *  row    col   val
 *  多少行   多少列   多少个有效值
 *  有效值出现的行列和值
 *
 * @author 王杰
 * @date 2019-07-19 16:58
 */

public class SparseArray {

    public static void main(String[] args) {
        //创建一个原始的二维数组
        // 0:表示没有棋子，1表示黑子  2表示篮子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][4] = 2;
        chessArr1[3][6] = 1;
        chessArr1[4][10] = 2;
        chessArr1[5][7] = 2;
        chessArr1[6][9] = 1;
        chessArr1[7][2] = 2;
        chessArr1[8][2] = 2;
        chessArr1[9][7] = 1;
        chessArr1[10][6] = 1;
        print(chessArr1);
        System.out.println("------------------------------");
        print(toSparseArray(chessArr1));
    }
    static class Data {
        private int row;
        private int line;
        private int val;
        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Data(int row, int line, int val) {
            this.row = row;
            this.line = line;
            this.val = val;
        }
    }

    /**
     *  将普通数组转换成二维数组
     *  思路 遍历原始数组得到有效值个数
     *  根据sum创建稀疏数组sparseArr int[sum+1][3]
     * @param chessArr 普通数组
     * @return 二维数组
     */
    private static int[][]  toSparseArray(int[][] chessArr) {
        //遍历数组 统计有效值
        int sum = 0;
        List<SparseArray.Data> list = new ArrayList<>();
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j]!=0) {
                    sum++;
                    list.add(new SparseArray.Data(i, j, chessArr[i][j]));
                }
            }
        }
        // 创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //第一行开始赋值  多少行 多少列  有效值
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = sum;
        // 遍历data放入稀疏数组中
        int row = 1;
        for (Data data : list) {
            
        }
        list.forEach(data-> sparseArr[data.row][data.line] = data.val);
        return sparseArr;
    }

    private static void print(int[][]chessArr) {
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }


}
