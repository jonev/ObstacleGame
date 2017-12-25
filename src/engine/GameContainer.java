package engine;// credit Majoowip on youtube

public class GameContainer implements Runnable{
    private Thread thread;
    private Window window;
    private Renderer renderer;
    private Input input;
    private AbstractGame game;

    private boolean running = false;
    private final double UPDATE_CAP = 0.5; //1.0/60.0;
    private int width = 10, height = 10;
    private float scale = 100f;
    private String title = "MajEngine v1.0";

    public GameContainer(AbstractGame game){
        this.game = game;
    }

    public void start(){
        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);
        thread = new Thread(this);
        thread.run(); // calls run(), futher down

    }

    public void stop(){

    }

    public void run(){
        running = true;

        boolean render = false;
        double firstTime = 0;
        double lastTime = System.nanoTime()/1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        while (running){
            render = false;
            firstTime = System.nanoTime()/1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;


            while (unprocessedTime >= UPDATE_CAP){

                unprocessedTime -= UPDATE_CAP;
                render = true;
                game.update(this, (float)UPDATE_CAP);
                input.update();
                if(frameTime >= 1.0){
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    System.out.println("FPS: " + fps);
                }

            }

            if(render){
                renderer.clear();
                game.render(this, renderer);
                window.update();
                frames++;
            } else {
                try{
                    Thread.sleep(1);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        dispose();
    }

    private void dispose(){

    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Thread getThread() {
        return thread;
    }

    public String getTitle() {
        return title;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getScale() {
        return scale;
    }

    public Window getWindow() {
        return window;
    }

    public Input getInput() {
        return input;
    }
}

