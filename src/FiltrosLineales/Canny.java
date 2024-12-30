/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FiltrosLineales;

/**
 *
 * @author tony
 */
public class Canny {
    

    private static final double GAUSSIAN_KERNEL[][] = {
        {2, 4, 5, 4, 2},
        {4, 9, 12, 9, 4},
        {5, 12, 15, 12, 5},
        {4, 9, 12, 9, 4},
        {2, 4, 5, 4, 2}
    };

    private static final int[][] SOBEL_X = {
        {-1, 0, 1},
        {-2, 0, 2},
        {-1, 0, 1}
    };

    private static final int[][] SOBEL_Y = {
        {-1, -2, -1},
        {0, 0, 0},
        {1, 2, 1}
    };

    public static double[][] applyCannyEdgeDetector(int[][] mtx, double lowThreshold, double highThreshold) {
        int width = mtx[0].length;
        int height = mtx.length;

        // Step 1: Apply Gaussian Blur
        double[][] blurred = applyGaussianBlur(mtx);

        // Step 2: Calculate gradient and direction
        double[][] gradient = new double[height][width];
        double[][] direction = new double[height][width];
        calculateGradientAndDirection(blurred, gradient, direction);

        // Step 3: Non-maximum suppression
        double[][] nonMaxSuppressed = nonMaximumSuppression(gradient, direction);

        // Step 4: Double threshold and edge tracking by hysteresis
        double[][] edges = doubleThresholdAndHysteresis(nonMaxSuppressed, lowThreshold, highThreshold);

        return edges;
    }

    private static double[][] applyGaussianBlur(int[][] mtx) {
        int width = mtx[0].length;
        int height = mtx.length;
        double[][] blurred = new double[height][width];

        int kernelSize = GAUSSIAN_KERNEL.length;
        int halfKernelSize = kernelSize / 2;

        for (int y = halfKernelSize; y < height - halfKernelSize; y++) {
            for (int x = halfKernelSize; x < width - halfKernelSize; x++) {
                double sum = 0.0;
                for (int ky = -halfKernelSize; ky <= halfKernelSize; ky++) {
                    for (int kx = -halfKernelSize; kx <= halfKernelSize; kx++) {
                        sum += mtx[y + ky][x + kx] * GAUSSIAN_KERNEL[ky + halfKernelSize][kx + halfKernelSize];
                    }
                }
                blurred[y][x] = sum / 159; // Normalize the Gaussian kernel sum
            }
        }

        return blurred;
    }

    private static void calculateGradientAndDirection(double[][] blurred, double[][] gradient, double[][] direction) {
        int width = blurred[0].length;
        int height = blurred.length;

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                double gx = 0.0;
                double gy = 0.0;

                for (int ky = -1; ky <= 1; ky++) {
                    for (int kx = -1; kx <= 1; kx++) {
                        gx += blurred[y + ky][x + kx] * SOBEL_X[ky + 1][kx + 1];
                        gy += blurred[y + ky][x + kx] * SOBEL_Y[ky + 1][kx + 1];
                    }
                }

                gradient[y][x] = Math.sqrt(gx * gx + gy * gy);
                direction[y][x] = Math.atan2(gy, gx);
            }
        }
    }

    private static double[][] nonMaximumSuppression(double[][] gradient, double[][] direction) {
        int width = gradient[0].length;
        int height = gradient.length;
        double[][] suppressed = new double[height][width];

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                double angle = direction[y][x] * (180.0 / Math.PI);
                angle = angle < 0 ? angle + 180 : angle;

                double q = 255;
                double r = 255;

                if ((0 <= angle && angle < 22.5) || (157.5 <= angle && angle <= 180)) {
                    q = gradient[y][x + 1];
                    r = gradient[y][x - 1];
                } else if (22.5 <= angle && angle < 67.5) {
                    q = gradient[y + 1][x - 1];
                    r = gradient[y - 1][x + 1];
                } else if (67.5 <= angle && angle < 112.5) {
                    q = gradient[y + 1][x];
                    r = gradient[y - 1][x];
                } else if (112.5 <= angle && angle < 157.5) {
                    q = gradient[y - 1][x - 1];
                    r = gradient[y + 1][x + 1];
                }

                if (gradient[y][x] >= q && gradient[y][x] >= r) {
                    suppressed[y][x] = gradient[y][x];
                } else {
                    suppressed[y][x] = 0;
                }
            }
        }

        return suppressed;
    }

    private static double[][] doubleThresholdAndHysteresis(double[][] suppressed, double lowThreshold, double highThreshold) {
        int width = suppressed[0].length;
        int height = suppressed.length;
        double[][] edges = new double[height][width];

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                if (suppressed[y][x] >= highThreshold) {
                    edges[y][x] = 255;
                } else if (suppressed[y][x] >= lowThreshold) {
                    edges[y][x] = 75;
                } else {
                    edges[y][x] = 0;
                }
            }
        }

        // Hysteresis
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                if (edges[y][x] == 75) {
                    if ((edges[y + 1][x - 1] == 255) || (edges[y + 1][x] == 255) || (edges[y + 1][x + 1] == 255) ||
                        (edges[y][x - 1] == 255) || (edges[y][x + 1] == 255) ||
                        (edges[y - 1][x - 1] == 255) || (edges[y - 1][x] == 255) || (edges[y - 1][x + 1] == 255)) {
                        edges[y][x] = 255;
                    } else {
                        edges[y][x] = 0;
                    }
                }
            }
        }

        return edges;
    }

   
}
