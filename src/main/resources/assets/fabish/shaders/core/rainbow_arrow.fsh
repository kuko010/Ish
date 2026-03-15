#version 150

uniform sampler2D Sampler0;
uniform sampler2D Sampler1;
uniform float GameTime;
uniform float GlobalPosX;
uniform float GlobalPosY;
uniform float GlobalPosZ;

in vec2 texCoord0;
in vec4 vertexColor;
out vec4 fragColor;

vec3 hueToRGB(float h) {
    vec3 rgb = abs(mod(h * 6.0 + vec3(0.0, 4.0, 2.0), 6.0) - 3.0) - 1.0;
    return clamp(rgb, 0.0, 1.0);
}

void main() {
    vec4 tex = texture(Sampler0, texCoord0);
    if (tex.a < 0.1) discard;

    // All operands are float — this is valid
    float hue = mod(GlobalPosX * 0.01 + GameTime * 0.5, 1.0);
    vec3 rainbow = hueToRGB(hue);

    fragColor = vec4(rainbow * tex.rgb, tex.a) * vertexColor;
}