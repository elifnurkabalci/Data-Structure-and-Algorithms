import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;

public class TextToImage {
    private String textpath;
    private String imagepath;

    public TextToImage(String text, String image){
        this.textpath = text;
        this.imagepath = image;
    }
    public String getImagepath() {
        return imagepath;
    }
    public String getTextpath() {
        return textpath;
    }
    public void turned(List<int[]> shortestPath){
        
        // Read the text file
        try {
            String textContent = new String(Files.readAllBytes(Paths.get(textpath)));

            // Get the dimensions of the text content
            String[] lines = textContent.split("\n");
            int width = lines[0].length();
            int height = lines.length;

            // Create a BufferedImage to represent the image
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();

            // Set the background color
            g2d.setBackground(Color.WHITE);
            g2d.clearRect(0, 0, width, height);

            // Set the font and color for drawing the text
            g2d.setColor(Color.BLACK);
            g2d.setFont(g2d.getFont().deriveFont(1));

            // Draw the text onto the image
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    char c = lines[y].charAt(x);
                    g2d.drawString(Character.toString(c), x, y);
                }
            }

            // Draw the path
            g2d.setColor(Color.RED);
            for (int[] coordinates : shortestPath) {
                int x = coordinates[0];
                int y = coordinates[1];
                g2d.fillRect(x, y, 1, 1);
            }

            // Dispose the Graphics2D object
            g2d.dispose();

            // Save the image as a PNG file
            ImageIO.write(image, "PNG", new File(imagepath));

            System.out.println("Image saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
