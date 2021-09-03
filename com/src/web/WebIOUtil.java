package web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class WebIOUtil {
  private static final int KILOBYTE_SIZE = 1024;
  /**
   * Copies webpage to html file byte by byte.
   * @param urlAddress
   * @param filename
   * @throws IOException
   */
  public static void copyURLPage(String urlAddress, String filename) throws IOException {
    try (InputStream inputStream = new URL(urlAddress).openStream();
        FileOutputStream outputStream = new FileOutputStream(filename)) {
      byte[] data = inputStream.readAllBytes();
      outputStream.write(data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  /**
   * Copies webpage to html file given buffer size in KB.
   * @param urlAddress
   * @param filename
   * @throws IOException
   */
  public static void copyURLPage(String urlAddress, String filename, int bufferSizeInKb) throws IOException {
    try (InputStream inputStream = new URL(urlAddress).openStream();
         FileOutputStream outputStream = new FileOutputStream(filename)) {
      byte[] buffer = new byte[bufferSizeInKb*KILOBYTE_SIZE];
      int numRead;
      double read = 0.0;
      while( (numRead = inputStream.read(buffer)) >= 0) {
        read += numRead;
        outputStream.write(buffer,0,numRead);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
