# Gotowe schematy do SpringBoot'a
### tworzenie obrazu i dekodowanie na byte64
```Java
 public BufferedImage image;

    public Image(){
        this.image =  new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB);

        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);

        graphics.fillRect(0, 0, 512, 512);
    }
    public String encodeToString() {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, "png", bos);
            byte[] imageBytes = bos.toByteArray();
            imageString = Base64.getEncoder().encodeToString(imageBytes);

            bos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageString;
    }
```
### Wyświetlanie obrazu 
```Java 
 static Image image = new Image();
    @RequestMapping("/image")// wiadomo mapping moze byc inny
    public String image(Model model){
        model.addAttribute("image", image.encodeToString());
        return "image";
    }
```
### ustawianie pixela
```Java
public class Pixel {
    public int x;
    public int y;
    public Color color;
    public String token;
    public Pixel(int x, int y, String color, String token) {
        this.x = x;
        this.y = y;
        this.color = Color.decode(color); // dekoduje kod z hexdecimal na biblioteke color w Javie
        this.token = token;
    }
```
### tworzenie pixela na obrazku
```Java
 public static void drawPixel(BufferedImage image, Pixel pixel){
        Graphics graphics = image.getGraphics();
        graphics.setColor(pixel.color);
        graphics.fillRect(pixel.x, pixel.y, 1, 1);
    }
```

