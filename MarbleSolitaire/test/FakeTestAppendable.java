import java.io.IOException;

/**
 * Appendable that will always throw an IOException.
 */
public class FakeTestAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Transmission failed.");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Transmission failed.");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Transmission failed.");
  }
}
