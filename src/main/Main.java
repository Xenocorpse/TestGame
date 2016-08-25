package main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;
import org.lwjgl.glfw.GLFWVidMode;

public class Main implements Runnable{
	
	private Thread thread;
	private long window;
	public boolean running = true;
	private boolean glTrue;

	public static void main(String[] args) {
		Main game = new Main();
		
		game.start();
	}

	private void start() {
		running = true;
		
		thread = new Thread(this, "EndlessRunner");
		
		thread.start();	
	}
	
	public void init() {
		if (GL_TRUE == 1) {
			glTrue = true;
		}
		
		//Initializes our window creator library - GLFW
		// This basically means, if this glfwInit() doesn't run properly
		// print an error to the console
		
		if (glfwInit() != glTrue) {
			
			// Throw an error
			
			System.err.println("GLFW initialization failed!");
		}
		
		// Allows our window to be resizable
		
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		
		//Creates the window
		window = glfwCreateWindow(680, 480, "Endless Runner", NULL, NULL);
		
		// Checks if window is properly created, otherwise prints an error
		if (window == NULL) {
			// Throw an error
			System.err.println("Couldn't create window!");
		}
		
		// Creates a GLFWVidMode object 'vidmode' which then queries
		// to see what the primary monitor is
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		
		// Sets initial window position
		glfwSetWindowPos(window, 100, 100);
		
		// Finally shows the created window
		glfwShowWindow(window);	
	}
	
	public void update() {
		glfwPollEvents();
	}
	
	public void render() {
		glfwSwapBuffers(window);
	}

	@Override
	public void run() {
		init();
		
		while(running) {
			
			update();
			
			render();	
			
			if(glfwWindowShouldClose(window) == glTrue) {
				running = false;
			}
		}
		
	}

}
