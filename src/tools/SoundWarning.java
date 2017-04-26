package tools;

import javax.sound.sampled.*;
import models.Configuration;

public class SoundWarning extends Thread {

	public void run() {
		SoundWarning.alert();
	}

	public static void alert() {
		int repeat = Configuration.getWarningRepeat();
		try {
			AudioFormat af = new AudioFormat(8000f, 8, 1, true, false);
			SourceDataLine sdl;
			sdl = AudioSystem.getSourceDataLine(af);
			sdl.open(af);
			sdl.start();

			byte[] buffer = new byte[1];

			for (int j = 0; j < repeat; j++) {
				for (int i = 0; i < 1600; i++) {
					double angle = i / (8000f / Configuration.getWarningSoundPitch() ) * 2.0 * Math.PI;
					buffer[0] = (byte)(Math.sin(angle) * 127.0);
					sdl.write(buffer,0,1);
				}
				Thread.sleep(Configuration.getWarningPause());
			}
			sdl.drain();
			sdl.stop();
			sdl.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}