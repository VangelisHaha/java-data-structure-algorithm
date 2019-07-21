package array;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
        int[][] chessArr = toSparseArray(chessArr1);
        print(chessArr);
        System.out.println("---------------转换后结果---------------");
        print(toArray(chessArr));
    }


    /**
     *  将数组保存在磁盘中
     * @param array 需要保存的数组
     */
    public static void wirte(int[][] array) {
        try {
            FileChannel outFileChannel = FileChannel.open(Paths.get("D:\\code\\java-data-structure-algorithm\\src\\array", "map.data")
            , StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  将二维数组转换成普通数组
     *  1. 读取
     * @param sparseArr 稀疏数组
     * @return 普通数组
     */
    private static int[][]  toArray(int[][] sparseArr) {
        int[][] array = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            array[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return array;
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
        for (int[] ints : chessArr) {
            for (int data : ints) {
                if (data != 0) {
                    sum++;
                }
            }
        }
        // 创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //第一行开始赋值  多少行 多少列  有效值
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = sum;
        //记录第几个不为0的值
        int count = 0;
        // 遍历data放入稀疏数组中
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                //所有不为0的位置记录它的行和列和值
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }
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
