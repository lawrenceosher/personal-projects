HOW TO USE THE IMAGE PROCESSOR IN GUI MODE:

1. Click the Load button and your computer's file directory will pop up. Navigate your directory so you can select the ppm, jpeg, bmp, or png image that you want to load in.
2. Click one of the operation buttons that correspond with a command that will be executed upon your image.
3. If you desire to brighten the image, type in the number value by which you want the image to be brightened by and click the brighten button after.
4. To save the image after you are done editing, type in the text box next to the Save button the name of the saved image with the extension (jpeg, png, ppm, or bmp) and click the Save button.

NOTES:
- Must click load and load an image before completing any operations on the image
- More than one operation can be applied to an image before saving
- Can only work on one image at a time. The image you are editing must be selected and display in the window. If you click load again and load in a different image, that image that you now loaded in will be the new image that will be edited.


Parts of the Program that are complete:

- An Image Histogram is created and visualized to correspond with the selected Image that was loaded. The x-axis of the histogram represents the pixel number values (from 0-255) and the y-axis represents the frequency, aka the number of occurrences, of that pixel value. The red line shows the red component of the pixel, the blue line shows the blue component of the pixel, the green line shows the the green component of the pixel, and the white line shows the intensity component of the pixel. For example, if there are 10 instances of the red components of pixels having a value of 25, then a point will be plotted at (25, 10) and the line graph will be updated.
- A Graphical User Interface (GUI) supported by Java Swing was made and each image operation is supported. These operations include load, save, blur, sharpen, greyscale, sepia, horizontal-flip, vertical-flip, brighten, luma-component, intensity-component, value-component, red-component, green-component, blue-component. 
- Testing of the GUI controller
- The image that is being displayed is scrollable.
- Appropriate error messages display on the screen.
- A new controller is created to support GUI mode for the image processor.
- Command-Line Arguments are supported:
	java -jar A6.jar -file -Commands.txt > runs the processor in script mode
	java -jar A6.jar -text > runs the processor in interactive text mode where a user can manually type in the images they want to process.
	java -jar A6.jar > GUI mode is run for the processor


*Instructions on using the Script Commands*
- type this script when the program runs
- run the .jar file and type "-file Commands.txt" to run the given script with all the commands

List of Possible Commands:
- load imgPath imgName (i.e "load res/koala.ppm koala")
- horizontal-flip image-name dest-image-name (i.e "horizontal-flip koala koalahorizontal")
- vertical-flip image-name dest-image-name (i.e "vertical-flip koalabright kbv")
- brighten increment image-name dest-image-name (i.e "brighten 10 koalabright kbv")
- red-component image-name dest-image-name (i.e "red-component koalabright kred")
- green-component image-name dest-image-name (i.e "green-component koalabright kgreen")
- blue-component image-name dest-image-name (i.e "blue-component koalabright kblue")
- value-component image-name dest-image-name (i.e "value-component koalabright kvalue")
- intensity-component image-name dest-image-name (i.e "intensity-component koalabright kintensity")
- luma-component image-name dest-image-name (i.e "luma-component koalabright kluma")
- save imagePath image-name (i.e "save res/koalaSaved.ppm koalabright")
- blur image-name dest-image-name (i.e "blur koala koalaBlur")
- sharpen image-name dest-image-name (i.e "sharpen koala koalaSharpen")
- greyscale image-name dest-image-name (i.e "greyscale koala koalaGrey")
- sepia image-name dest-image-name (i.e "sepia koala koalaSepia")

ICommands interface represents possible operations that can be utilized in the Image Processing application. Has a method that implements the functionality for each command and two methods to get the name of the original image and the modified image, respectively.

The IImageProcessorController interface represents a controller which tells the model what command to apply to the a given image.

ImageProcessorControllerImpl represents a controller that interacts with the model by taking in an input from the user, a command, and gives it to the model to implement that command to run on what image.

IImageProcessorModel interface represents an image processing model that can return a stored image and execute commands on these images.

ImageProcessorModelImpl implements IImageProcessorModel and is an image processing model that stores IImages and execute commands received from the controller, on the stored Image.

IImage interface represent images that have a height and a width, array of Pixels and MaxValue as well as the ability to swap two pixels in any part of the image.

Image class implements IImage and represents an Image storing an array of pixels and contains methods to get information from Image (width, height, maxValue, array). model will apply commands to the array of pixels in Image class.

IPixel interface represents one Pixel of an image and has methods to get information about the pixel (Red, Green, Blue component, maxValue, Luma, and Intensity value of Pixel).

Pixel class implements IPixel and stores the information for one pixel of an IImage implementing all the getters from interface.

ImageUtil class is a utility class containing the method to read PPM files and returning a Scanner that is used in the controller when scanning text files for commands and used in the Load class when loading an image file.

ACommand class represents an Abstract Command that applies the go method and modifies the source file in the given model. Contains methods to get both the Source and Destination File.

AVisualize is an abstract class that extends ACommand representing a command that visualizes a component of a pixel (Red,Blue,Green, Luma, intensity or maxValue) and has a method to return the needed component of the given Pixel which the model uses to modify the IImage.

AFilter is an abstract class that extends ACommand and it represents a filter command that will apply a kernel to an image and change each pixel in the image depending on the image around it. Has a method that will return the modified image after the kernel is applied. Has a method that multiples the transformation matrix by the matrix of the corresponding channels of each pixel. Has a method that returns arrays of each channel of the new pixel for each image.

AColorTransformation is an abstract class that extends ACommand and it represents a color transformation command that converts color images into images of different shades based on the transformation matrix and the channels of each pixel. Has a method that will return the modified image after the transformation matrix is applied to each channel of each pixel.

The following classes implement the ICommands interface:

Brighten class represents a Command that brightens or darkens given source file and updates it to the destination file name.

Load Command loads the given image, if not found throws an exception. 

VerticalFlip class flips the given image vertically and updates it to the image name.

HorizontalFlip class flips the given image horizontally and updates it to the image name.

Save class writes a new file with the given Image and saves it to the given image Path.

VisualizeLuma takes the source image and sets the pixels to its luma value.

VisualizeIntensity sets the pixels of the source image to the average of the three components of each pixel.

VisualizeRed class sets the pixels of the source image 2 to the Red value of each pixel.

VisualizeBlue class sets the pixels of the source image 2 to the Blue value of each pixel.

VisualizeGreen class sets the pixels of the source image 2 to the Green value of each pixel.

VisualizeValue class sets the pixels of the source image to the maximum value of the three components of each pixel.

Blur class extends AFilter class and it blurs the source image based on the kernel that will change each new pixel based on its surrounding pixels. Specifically, a Gaussian blur matrix is used.

Sharpen class extends AFilter class and it sharpens the source image based on a kernel and it will change each pixel based on its surrounding pixels to accentuate the edges.

Greyscale class extends AColorTransformation class and it converts an image from color into an image that is made up of different shades of grey. 

Sepia class extends AColorTransformation class and it converts a color image into a sepia image that has a reddish brown tone to it.

MockModel implements the IImageProcessorModel interface and is used to test the inputs the model receives from the controller (the command the model should execute on given file) when running program.

Changes from Assignment 4:

- Any instance of IPixel was changed to Pixel to ensure that no concrete classes were called when declaring new fields, objects, or variables. Supports interface polymorphism in case new types are pixels are ever used again in the future.
- Deleted the ImageUtil class since all of the functionality was used in Load and the methods were split into readPPM and read for other image extensions.
- Updated HashMap of supported commands in the controller to support blur, sharpen, greyscale, and sepia.

Changes from Assignment 5:

- Added IGUIView and Features interfaces. IGUIView represents a type of GUI view that the Image Processor Application will support. Features represents the different requests that the user will make when interacting with the GUI.
- Added ImageGUIControllerImpl that implements IImageGUIController that acts as the controller between the model and GUI View and allows the buttons pressed in the view to be translated into the model's functionality.
- Added HistogramPanel class so that the user can see the corresponding Histogram for the pixel components of red, blue, green, and intensity.
- Added the GUIView class that implements the IGUIView interface and it is supported by Java Swing to display a GUI that has buttons for image operations, a window for the current image selected, and a window for the corresponding histogram for the image.
- Enhanced Main class to support the different modes for running the ImageProcessor. Either GUI mode(no command line arguments), interactive text mode (-text as a CLA), and script mode (-file -path-of-script-file as a CLA). This change to the Main class so the program can be used in more ways than one.
- Added MockGUI that implements IGUIView and JFrame and is used to test the actions the controller receives from the view. Added MockController that implements IImageGUIcontroller and ActionListener and is used to for testing purposes. Finally, the GUIController test class was implemented for testing purposes.
- Added IImageGUIcontroller as a new interface for a GUI controller for the image processor.


Image Citation: beach.jpeg was obtained from Unsplash. Link to original image: https://unsplash.com/photos/dyHjGsaQacs

Terms of usage are outlined with this link: https://unsplash.com/license