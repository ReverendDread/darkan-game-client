import jaggl.OpenGL;

public class Class137_Sub3 extends Class137 {

	int anInt9105;

	void method14460(boolean bool_1) {
		this.aClass505_Sub1_1646.method13654(this);
		OpenGL.glTexParameteri(this.anInt1648, 10242, 33071);
	}

	Class137_Sub3(OpenGLGraphicalRenderer class505_sub1_1, Class150 class150_2, Class76 class76_3, int i_4, byte[] bytes_5, Class150 class150_6) {
		super(class505_sub1_1, 3552, class150_2, class76_3, i_4, false);
		this.anInt9105 = i_4;
		this.aClass505_Sub1_1646.method13654(this);
		OpenGL.glPixelStorei(3317, 1);
		OpenGL.glTexImage1Dub(this.anInt1648, 0, OpenGLGraphicalRenderer.method13630(this.aClass150_1650, this.aClass76_1651), this.anInt9105, 0, OpenGLGraphicalRenderer.method13629(class150_6), 5121, bytes_5, 0);
		OpenGL.glPixelStorei(3317, 4);
		this.method2351(true);
	}

}
