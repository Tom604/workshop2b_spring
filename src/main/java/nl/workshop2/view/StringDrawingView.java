package nl.workshop2.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class StringDrawingView {

    public void drawBeginString() {
        
        System.out.println("\n*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*\n");
        
        Font font = new Font("Arial Narrow", Font.PLAIN, 18);
        Settings settings = new Settings(font, 73, 25);
        
        drawString(" Boer Piets", "#", settings);
        System.out.println();
        drawString("    Kazen", "#", settings);
    }
    
    public void drawEndString() {
        
        Font font = new Font("Arial Narrow", Font.PLAIN, 20);
        Settings settings = new Settings(font, 73, 25);
        
        drawString("Tot ziens!", "#", settings);
    }
    
    public void drawString(String text, String artChar, Settings settings) {
        
        BufferedImage image = getImageIntegerMode(settings.width, settings.height);

        Graphics2D graphics2D = getGraphics2D(image.getGraphics(), settings);
        graphics2D.drawString(text, 0, ((int) (settings.height * 0.67)));

        for (int y = 0; y < settings.height; y++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int x = 0; x < settings.width; x++) {
                stringBuilder.append(image.getRGB(x, y) == -16777216 ? " " : artChar);
            }
            if (stringBuilder.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(stringBuilder);
        }
    }

    private BufferedImage getImageIntegerMode(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    private Graphics2D getGraphics2D(Graphics graphics, Settings settings) {
        
        graphics.setFont(settings.font);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        return graphics2D;
    }

    public class Settings {
        public Font font;
        public int width;
        public int height;

        public Settings(Font font, int width, int height) {
            this.font = font;
            this.width = width;
            this.height = height;
        }
    }
}
