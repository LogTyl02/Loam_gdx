#define PI 3.14

// Inputs from the vector shader
varying vec vTexCoord0;

// Uniform values
uniform sampler2D u_texture;
uniform vec2 resolution;

// Alpha threshold for the occlusion map
const float THRESHOLD = 0.75;

void main(void) {
	float distance = 1.0;
	
	for (float y = 0.0; y < resolution.y; y += 1.0) {
		// Rectangular to polar filter
		vec2 norm = vec2(vTexCoord0.s, y / resolution.y) * 2.0 - 1.0;
		float theta = PI * 1.5 + norm.x * PI;
		float r = (1.0 + norm.y) * 0.5;
		
		// Coord which will sample from occlusion map
		vec2 coord = vec2(-r * sin(theta), -r * cos(theta)) / 2.0 + 0.5;
		
		// Sample occlusion map
		vec4 data = texture2D(u_texture, coord);
		
		float dst = y / resolution.y;
		
		// If we've hit an opaque pixel, get new distance
		// If new distance is below current, use that ray
		
		float caster = data.a;
		if (caster > THRESHOLD) {
			distance = min(distance, dst);
			
		}
	}
	gl_FragColor = vec4(vec3(distance), 1.0);
}