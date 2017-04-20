package tools;

import javax.sound.sampled.*;

public class SoundCritical extends Thread {

  public void run() {
    SoundCritical.alert();
  }

  public static void alert() {
    int repeat = 10;
    try {
      AudioFormat af = new AudioFormat(8000f, 8, 1, true, false);
      SourceDataLine sdl;
      sdl = AudioSystem.getSourceDataLine(af);
      sdl.open(af);
      sdl.start();

      byte[] buffer = new byte[1];
      int step;

      for (int j = 0; j < repeat; j++) {
        step = 10;
        for (int i = 0; i < 2000; i++) {
          buffer[0] = ((i % step > 0) ? 32 : (byte) 0);

          if (i % 250 == 0)
            step += 2;
          sdl.write(buffer, 0, 1);
        }
        Thread.sleep(200);
      }
      sdl.drain();
      sdl.stop();
      sdl.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}