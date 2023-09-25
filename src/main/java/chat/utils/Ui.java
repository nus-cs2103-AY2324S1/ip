package chat.utils;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Ui {

    private static final String[] nameList = {"Chat", "ninja", "xqc", "jerma", "pokimane", "dream", "fortnite", "moist critical", "quackity"};
    private static final Color[] colorList = {Color.LIGHTSALMON, Color.LIMEGREEN, Color.LIGHTCYAN, Color.WHITE, Color.PINK, Color.CORAL, Color.GOLDENROD};
    private Image[] imageList;
    private Image pepeImage;
    private Image ninjaImage;
    private Image xqcImage;
    private Image shroudImage;
    private Image pokimaneImage;
    private Image dreamImage;
    private Image fortniteImage;
    private Image criticalImage;
    private Image quackityImage;

    public static class ChatWrapper{
        public String chatName;
        public Image chatImage;
        public Color chatColor;

        public ChatWrapper(String chatName, Image chatImage, Color chatColor) {
            this.chatName = chatName;
            this.chatImage = chatImage;
            this.chatColor = chatColor;
        }
    }

    public Ui() {
        pepeImage = new Image(this.getClass().getResourceAsStream("/images/defaultpepe.jpg"));
        ninjaImage = new Image(this.getClass().getResourceAsStream("/images/ninja.jpg"));
        xqcImage = new Image(this.getClass().getResourceAsStream("/images/xqc.jpg"));
        shroudImage = new Image(this.getClass().getResourceAsStream("/images/jerma.jpg"));
        pokimaneImage = new Image(this.getClass().getResourceAsStream("/images/poki.jpeg"));
        dreamImage = new Image(this.getClass().getResourceAsStream("/images/dream.jpg"));
        fortniteImage = new Image(this.getClass().getResourceAsStream("/images/fortnite.jpg"));
        criticalImage = new Image(this.getClass().getResourceAsStream("/images/critical.jpg"));
        quackityImage = new Image(this.getClass().getResourceAsStream("/images/tate.png"));
        imageList = new Image[] {pepeImage, ninjaImage, xqcImage, shroudImage, pokimaneImage, dreamImage, fortniteImage, criticalImage, quackityImage};
    }

    public ChatWrapper getChatWrapper() {
        int randomNameIndex = (int) (Math.random() * nameList.length);
        int randomColorIndex = (int) (Math.random() * colorList.length);
        String chatName = nameList[randomNameIndex] + ": ";
        Image chatImage = imageList[randomNameIndex];
        Color chatColor = colorList[randomColorIndex];
        return new ChatWrapper(chatName, chatImage, chatColor);
    }
    
}
