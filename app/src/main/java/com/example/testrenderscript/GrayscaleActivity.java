package com.example.testrenderscript;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.renderscript.Allocation;
import androidx.renderscript.RenderScript;
import com.example.renderscriptexample.ScriptC_grayscale;

public class GrayscaleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Đọc ảnh từ resource
        Bitmap colorBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sample_image);
        Bitmap grayscaleBitmap = Bitmap.createBitmap(colorBitmap.getWidth(), colorBitmap.getHeight(), colorBitmap.getConfig());

        // Chuyển ảnh sang đen trắng
        convertToGrayscale(colorBitmap, grayscaleBitmap);

        // Hiển thị ảnh đen trắng
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageBitmap(grayscaleBitmap);
    }

    private void convertToGrayscale(Bitmap input, Bitmap output) {
        // Tạo RenderScript context
        RenderScript rs = RenderScript.create(this);

        // Tạo Allocation cho ảnh đầu vào và đầu ra
        Allocation inputAllocation = Allocation.createFromBitmap(rs, input);
        Allocation outputAllocation = Allocation.createFromBitmap(rs, output);

        // Tạo script từ mã RenderScript
        ScriptC_grayscale script = new ScriptC_grayscale(rs);

        // Truyền Allocation đầu vào và đầu ra
        script.set_inputImage(inputAllocation);
        script.set_outputImage(outputAllocation);

        // Chạy kernel toGrayscale
        script.forEach_toGrayscale(inputAllocation, outputAllocation);

        // Copy kết quả từ Allocation sang bitmap đầu ra
        outputAllocation.copyTo(output);

        // Giải phóng RenderScript
        rs.destroy();
    }
}
