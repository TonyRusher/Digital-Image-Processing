/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OperateImages;

import Model.ImageData;
import java.awt.Color;

/**
 *
 * @author tony
 */
public class Operate {

    ImageData img1, img2;
    int height, width;
    int[][] mtx1;
    int[][] mtx2;
    int [][]mtxAns;

    public Operate(ImageData img1, ImageData img2) {
        System.out.println("CONSTRUCTOR");
        this.img1 = img1;
        this.img2 = img2;
        this.height = Math.min(img1.getHeight(), img2.getHeight());
        this.width = Math.min(img1.getWidth(), img2.getWidth());
        this.mtx1 = img1.getChannelInt(0);
        this.mtx2 = img2.getChannelInt(0);
        mtxAns = new int[height][width];
    }

    public ImageData suma() {
        System.out.println("SUMAAAAAAAAAA");
        mtxAns = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = (mtx1[i][j] + mtx2[i][j]) / 2;
//                int value = (i%2==0?255:0);

                mtxAns[i][j] = value << 16 | value << 8 | value;
            }
        }
        
        return new ImageData(mtxAns);
    }

    public ImageData resta() {
        mtxAns = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = Math.abs(mtx1[i][j] - mtx2[i][j]);
                mtxAns[i][j] = value << 16 | value << 8 | value;
            }
        }
        return new ImageData(mtxAns);
    }

    public ImageData multiplicacion() {
        mtxAns = new int[height][width];
        double[][] mtxAns1 = new double[height][width];
        double maxx = -1000000000, minn = 1000000000;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double value = mtx1[i][j] * mtx2[i][j];
                mtxAns1[i][j] = value;
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maxx = Math.max(maxx, mtxAns1[i][j]);
                minn = Math.min(minn, mtxAns1[i][j]);
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mtxAns1[i][j] = (mtxAns1[i][j] - minn) * (255.0 / (maxx - minn));
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = (int) mtxAns1[i][j];
                mtxAns[i][j] = value << 16 | value << 8 | value;;
            }
        }
        return new ImageData(mtxAns);
    }

    public ImageData division() {
        mtxAns = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = 0;
                if(mtx2[i][j] == 0){
                    value = (int)((double)mtx1[i][j] / 0.01) & 0xFF;
                }else{
                     value = (int)((double)mtx1[i][j] / (double)mtx2[i][j]);
                }
                mtxAns[i][j] = value << 16 | value << 8 | value;
            }
        }

        return new ImageData(mtxAns);
    }

    public ImageData AND() {
        mtxAns = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = mtx1[i][j] & mtx2[i][j];
                mtxAns[i][j] = value << 16 | value << 8 | value;;
            }
        }

        return new ImageData(mtxAns);
    }

    public ImageData OR() {
        mtxAns = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = (mtx1[i][j] | mtx2[i][j]);
                Color color = new Color(value,value,value);
                mtxAns[i][j] = color.getRGB();
            }
        }

        return new ImageData(mtxAns);
    }

    public ImageData XOR() {
        mtxAns = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = mtx1[i][j] ^ mtx2[i][j];
                mtxAns[i][j] = value << 16 | value << 8 | value;;
            }
        }

        return new ImageData(mtxAns);
    }

    public ImageData menorIgual() {
        mtxAns = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = mtx1[i][j] <= mtx2[i][j] ? 255 : 0;
                mtxAns[i][j] = value << 16 | value << 8 | value;;
            }
        }

        return new ImageData(mtxAns);
    }

    public ImageData menor() {
        mtxAns = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = mtx1[i][j] < mtx2[i][j] ? 255 : 0;
                mtxAns[i][j] = value << 16 | value << 8 | value;;
            }
        }

        return new ImageData(mtxAns);
    }

    public ImageData mayorIgual() {
        mtxAns = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = mtx1[i][j] >= mtx2[i][j] ? 255 : 0;
                mtxAns[i][j] = value << 16 | value << 8 | value;;
            }
        }

        return new ImageData(mtxAns);
    }

    public ImageData mayor() {
        mtxAns = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = mtx1[i][j] > mtx2[i][j] ? 255 : 0;
                mtxAns[i][j] = value << 16 | value << 8 | value;;
            }
        }

        return new ImageData(mtxAns);
    }

    public ImageData igual() {
        mtxAns = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = mtx1[i][j] == mtx2[i][j] ? 255 : 0;
                mtxAns[i][j] = value << 16 | value << 8 | value;;
            }
        }

        return new ImageData(mtxAns);
    }

    public ImageData diferente() {
        mtxAns = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = mtx1[i][j] != mtx2[i][j] ? 255 : 0;
                mtxAns[i][j] = value << 16 | value << 8 | value;;
            }
        }

        return new ImageData(mtxAns);
    }

    public ImageData NOT() {
        mtxAns = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = 255 - mtx1[i][j];
                mtxAns[i][j] = value << 16 | value << 8 | value;;
            }
        }

        return new ImageData(mtxAns);
    }

    @Override
    public String toString() {
        String ans = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
               ans += "|" + mtxAns[i][j] + "|";
            }
        }
        return ans;
    }

}
