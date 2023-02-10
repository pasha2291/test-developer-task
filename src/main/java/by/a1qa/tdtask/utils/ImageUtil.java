package by.a1qa.tdtask.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import net.coobird.thumbnailator.Thumbnails;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@UtilityClass
public class ImageUtil {
    private static final String FORMAT = "JPEG";
    private static final double OUTPUT_QUALITY = 0.7;
    private static final int TARGET_WIDTH = 400;
    private static final int TARGET_HEIGHT = 200;

    @SneakyThrows
    public static byte[] reduceImage(byte[] initialImage) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(initialImage);
        Thumbnails.of(inputStream)
                .size(TARGET_WIDTH, TARGET_HEIGHT)
                .outputFormat(FORMAT)
                .outputQuality(OUTPUT_QUALITY)
                .toOutputStream(outputStream);
        byte[] sizedImage = outputStream.toByteArray();
        inputStream.close();
        outputStream.close();
        return sizedImage;
    }
}
