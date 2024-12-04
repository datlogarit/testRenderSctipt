#pragma version(1)
#pragma rs java_package_name(com.example.renderscriptexample)

uchar4 RS_KERNEL brighten(uchar4 in, uint32_t x, uint32_t y) {
    uchar4 out = in;
    out.r = clamp(out.r + 50, 0, 255);
    out.g = clamp(out.g + 50, 0, 255);
    out.b = clamp(out.b + 50, 0, 255);
    return out;
}
