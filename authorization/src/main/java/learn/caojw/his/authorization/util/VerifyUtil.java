package learn.caojw.his.authorization.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class VerifyUtil {
    private final static Random RANDOM = new Random();

    private static String generateExpression() {
        int number1 = RANDOM.nextInt(9) + 1;
        char symbol = new char[]{'+', '-', '*', '/'}[RANDOM.nextInt(4)];
        int number2 = RANDOM.nextInt(number1);
        return switch (symbol) {
            case '+' -> String.format("%d%c%d=%d", number1, symbol, number2, number1 + number2);
            case '-' -> String.format("%d%c%d=%d", number1, symbol, number2, number1 - number2);
            case '*' -> String.format("%d%c%d=%d", number1, symbol, number2, number1 * number2);
            case '/' -> String.format("%d%c%d=%d", number1 * number2, symbol, number1, number2);
            default -> throw new RuntimeException();
        };
    }

    private static void drawExpression(String expression, Font font, BufferedImage bufferedImage) {
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setFont(font);
        int length = bufferedImage.getWidth() / expression.length();
        int y = bufferedImage.getHeight() / 2 + font.getSize() / 2;
        for (int i = 0; i < expression.length(); i++) {
            int x = i * length + length / 2 - font.getSize() / 4;
            double rotate = RANDOM.nextDouble(Math.PI / 4);
            if (i == expression.length() - 1) {
                rotate *= -1;
            } else if (i == expression.length() - 2) {
                rotate *= 0;
            } else {
                rotate *= 1;
            }
            graphics.rotate(rotate, x + font.getSize() / 4.0, y - font.getSize() / 2.0);
            graphics.setColor(new Color(RANDOM.nextInt(256), RANDOM.nextInt(256), RANDOM.nextInt(256)));
            graphics.drawString(String.valueOf(expression.charAt(i)), x, y);
            graphics.rotate(-rotate, x + font.getSize() / 4.0, y - font.getSize() / 2.0);
        }
        graphics.dispose();
    }

    private static int[][][] generateInterference(int width, int height) {
        int[][][] interferences = new int[2][2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                interferences[i][j][0] = RANDOM.nextInt(width);
                interferences[i][j][1] = RANDOM.nextInt(height);
            }
        }
        return interferences;
    }

    private static void drawInterference(int[][][] interferences, BufferedImage bufferedImage) {
        Graphics2D graphics = bufferedImage.createGraphics();
        for (int[][] interference : interferences) {
            graphics.setColor(new Color(RANDOM.nextInt(256), RANDOM.nextInt(256), RANDOM.nextInt(256)));
            graphics.drawLine(interference[0][0], interference[0][1], interference[1][0], interference[1][1]);
        }
        graphics.dispose();
    }

    public static int generate(int width, int height, int fontSize, OutputStream outputStream) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        String expression = generateExpression();
        drawExpression(expression.split("=")[0], new Font("Times New Roman", Font.PLAIN, fontSize), bufferedImage);
        int[][][] interferences = generateInterference(width, height);
        drawInterference(interferences, bufferedImage);
        ImageIO.write(bufferedImage, "png", outputStream);
        return Integer.parseInt(expression.split("=")[1]);
    }
}
