package showPic;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

// http://stackoverflow.com/questions/15670933/opencv-java-load-image-to-gui
public class MatToBufImg {

	public static BufferedImage toBufferedImage(Mat m) {
		int type = BufferedImage.TYPE_BYTE_GRAY;
		if (m.channels() > 1) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		int bufferSize = m.channels() * m.cols() * m.rows();
		byte[] b = new byte[bufferSize];
		m.get(0, 0, b); // get all the pixels
		BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
		final byte[] targetPixels = ((DataBufferByte) image.getRaster()
				.getDataBuffer()).getData();
		System.arraycopy(b, 0, targetPixels, 0, b.length);
		return image;
	}
	
	// http://enfanote.blogspot.hu/2013/06/converting-java-bufferedimage-to-opencv.html
	public static Mat toMat(BufferedImage bi){
		byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
		Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
		mat.put(0, 0, data);
		
		return mat;
	}
	
	public static void matToImg(Mat m, String fileName){
		org.opencv.imgcodecs.Imgcodecs.imwrite(fileName, m);
	}
}
