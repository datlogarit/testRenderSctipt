#pragma version(1)
#pragma rs java_package_name(com.example.renderscriptexample)

// Biến đầu vào và đầu ra
rs_allocation inputImage;
rs_allocation outputImage;

// Hàm chuyển đổi màu thành đen trắng
uchar4 __attribute__((kernel)) toGrayscale(uchar4 inPixel, uint32_t x, uint32_t y) {
    // Tính giá trị trung bình
    float gray = 0.299f * inPixel.r + 0.587f * inPixel.g + 0.114f * inPixel.b;

    // Gán giá trị đen trắng
    uchar4 outPixel = inPixel;
    outPixel.r = (uchar)gray;
    outPixel.g = (uchar)gray;
    outPixel.b = (uchar)gray;

    return outPixel;
}
