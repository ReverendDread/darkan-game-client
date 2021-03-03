package com.jagex;

import com.jagex.client.js5.defs.animations.AnimationFrame;
import com.jagex.client.js5.defs.animations.AnimationFrameBase;
import com.jagex.client.js5.defs.animations.AnimationFrameSet;
import com.jagex.client.js5.defs.models.RSMesh;
import com.jagex.unknown.Class83;

import java.util.Arrays;

public abstract class MeshRasterizer {

	protected boolean aBool7023;

	public abstract void bc(int var1, int var2, Ground var3, Ground var4, int var5, int var6, int var7);

	public abstract ParticleEmitterConfig[] method11253();

	public abstract int o();

	public int method11254(float f_1, float f_2, float f_3) {
		float f_4 = f_1 < 0.0F ? -f_1 : f_1;
		float f_5 = f_2 < 0.0F ? -f_2 : f_2;
		float f_6 = f_3 < 0.0F ? -f_3 : f_3;
		return f_5 > f_4 && f_5 > f_6 ? (f_2 > 0.0F ? 0 : 1) : (f_6 > f_4 && f_6 > f_5 ? (f_3 > 0.0F ? 2 : 3) : (f_1 > 0.0F ? 4 : 5));
	}

	public void method11255(int i_1, int i_2, int i_3, int i_4, int i_5, int i_6, int i_7, float[] floats_8, int i_9, float f_10, float f_11, float f_12, float[] floats_13) {
		i_1 -= i_4;
		i_2 -= i_5;
		i_3 -= i_6;
		float f_14 = i_1 * floats_8[0] + i_2 * floats_8[1] + i_3 * floats_8[2];
		float f_15 = i_1 * floats_8[3] + i_2 * floats_8[4] + i_3 * floats_8[5];
		float f_16 = i_1 * floats_8[6] + i_2 * floats_8[7] + i_3 * floats_8[8];
		float f_17;
		float f_18;
		if (i_7 == 0) {
			f_17 = f_14 + f_10 + 0.5F;
			f_18 = -f_16 + f_12 + 0.5F;
		} else if (i_7 == 1) {
			f_17 = f_14 + f_10 + 0.5F;
			f_18 = f_16 + f_12 + 0.5F;
		} else if (i_7 == 2) {
			f_17 = -f_14 + f_10 + 0.5F;
			f_18 = -f_15 + f_11 + 0.5F;
		} else if (i_7 == 3) {
			f_17 = f_14 + f_10 + 0.5F;
			f_18 = -f_15 + f_11 + 0.5F;
		} else if (i_7 == 4) {
			f_17 = f_16 + f_12 + 0.5F;
			f_18 = -f_15 + f_11 + 0.5F;
		} else {
			f_17 = -f_16 + f_12 + 0.5F;
			f_18 = -f_15 + f_11 + 0.5F;
		}

		float f_19;
		if (i_9 == 1) {
			f_19 = f_17;
			f_17 = -f_18;
			f_18 = f_19;
		} else if (i_9 == 2) {
			f_17 = -f_17;
			f_18 = -f_18;
		} else if (i_9 == 3) {
			f_19 = f_17;
			f_17 = f_18;
			f_18 = -f_19;
		}

		floats_13[0] = f_17;
		floats_13[1] = f_18;
	}

	public Class83 method11256(RSMesh mesh, int[] triangles, int triangleCount) {
		int[] triX = null;
		int[] ints_5 = null;
		int[] ints_6 = null;
		float[][] pixels = null;
		if (mesh.texturePos != null) {
			int i_8 = mesh.texturedFaceCount;
			int[] ints_9 = new int[i_8];
			int[] ints_10 = new int[i_8];
			int[] ints_11 = new int[i_8];
			int[] ints_12 = new int[i_8];
			int[] ints_13 = new int[i_8];
			int[] ints_14 = new int[i_8];

			int index;
			for (index = 0; index < i_8; index++) {
				ints_9[index] = Integer.MAX_VALUE;
				ints_10[index] = -2147483647;
				ints_11[index] = Integer.MAX_VALUE;
				ints_12[index] = -2147483647;
				ints_13[index] = Integer.MAX_VALUE;
				ints_14[index] = -2147483647;
			}

			int vertexX;
			for (index = 0; index < triangleCount; index++) {
				int face = triangles[index];
				if (mesh.texturePos[face] != -1) {
					int type = mesh.texturePos[face] & 0xff;

					for (int side = 0; side < 3; side++) {
						short triangle;
						if (side == 0)
							triangle = mesh.triangleX[face];
						else if (side == 1)
							triangle = mesh.triangleY[face];
						else
							triangle = mesh.triangleZ[face];

						vertexX = mesh.vertexX[triangle];
						int vertexY = mesh.vertexY[triangle];
						int vertexZ = mesh.vertexZ[triangle];
						if (vertexX < ints_9[type]) {
							ints_9[type] = vertexX;
						}

						if (vertexX > ints_10[type]) {
							ints_10[type] = vertexX;
						}

						if (vertexY < ints_11[type]) {
							ints_11[type] = vertexY;
						}

						if (vertexY > ints_12[type]) {
							ints_12[type] = vertexY;
						}

						if (vertexZ < ints_13[type]) {
							ints_13[type] = vertexZ;
						}

						if (vertexZ > ints_14[type]) {
							ints_14[type] = vertexZ;
						}
					}
				}
			}

			triX = new int[i_8];
			ints_5 = new int[i_8];
			ints_6 = new int[i_8];
			pixels = new float[i_8][];

			for (index = 0; index < i_8; index++) {
				byte type = mesh.textureRenderTypes[index];
				if (type > 0) {
					triX[index] = (ints_9[index] + ints_10[index]) / 2;
					ints_5[index] = (ints_11[index] + ints_12[index]) / 2;
					ints_6[index] = (ints_13[index] + ints_14[index]) / 2;
					float x;
					float y;
					float z;
					if (type == 1) {
						vertexX = mesh.particleDirectionX[index];
						if (vertexX == 0) {
							x = 1.0F;
							z = 1.0F;
						} else if (vertexX > 0) {
							x = 1.0F;
							z = vertexX / 1024.0F;
						} else {
							z = 1.0F;
							x = (-vertexX) / 1024.0F;
						}

						y = 64.0F / mesh.particleDirectionY[index];
					} else if (type == 2) {
						x = 64.0F / mesh.particleDirectionX[index];
						y = 64.0F / mesh.particleDirectionY[index];
						z = 64.0F / mesh.particleDirectionZ[index];
					} else {
						x = mesh.particleDirectionX[index] / 1024.0F;
						y = mesh.particleDirectionY[index] / 1024.0F;
						z = mesh.particleDirectionZ[index] / 1024.0F;
					}

					pixels[index] = method11257(mesh.texTriX[index], mesh.texTriY[index], mesh.texTriZ[index], mesh.particleLifespanX[index] & 0xff, x, y, z);
				}
			}
		}

		return new Class83(this, triX, ints_5, ints_6, pixels);
	}

	public float[] method11257(int i_1, int i_2, int i_3, int i_4, float f_5, float f_6, float f_7) {
		float[] floats_8 = new float[9];
		float[] floats_9 = new float[9];
		float f_10 = (float) Math.cos(i_4 * 0.024543693F);
		float f_11 = (float) Math.sin(i_4 * 0.024543693F);
		float f_12 = 1.0F - f_10;
		floats_8[0] = f_10;
		floats_8[1] = 0.0F;
		floats_8[2] = f_11;
		floats_8[3] = 0.0F;
		floats_8[4] = 1.0F;
		floats_8[5] = 0.0F;
		floats_8[6] = -f_11;
		floats_8[7] = 0.0F;
		floats_8[8] = f_10;
		float[] floats_13 = new float[9];
		float f_14 = 1.0F;
		float f_15 = 0.0F;
		f_10 = i_2 / 32767.0F;
		f_11 = -((float) Math.sqrt(1.0F - f_10 * f_10));
		f_12 = 1.0F - f_10;
		float f_16 = (float) Math.sqrt(i_3 * i_3 + i_1 * i_1);
		if (f_16 == 0.0F && f_10 == 0.0F) {
			floats_9 = floats_8;
		} else {
			if (f_16 != 0.0F) {
				f_14 = (-i_3) / f_16;
				f_15 = i_1 / f_16;
			}

			floats_13[0] = f_10 + f_14 * f_14 * f_12;
			floats_13[1] = f_15 * f_11;
			floats_13[2] = f_15 * f_14 * f_12;
			floats_13[3] = -f_15 * f_11;
			floats_13[4] = f_10;
			floats_13[5] = f_14 * f_11;
			floats_13[6] = f_14 * f_15 * f_12;
			floats_13[7] = -f_14 * f_11;
			floats_13[8] = f_10 + f_15 * f_15 * f_12;
			floats_9[0] = floats_8[0] * floats_13[0] + floats_8[1] * floats_13[3] + floats_8[2] * floats_13[6];
			floats_9[1] = floats_8[0] * floats_13[1] + floats_8[1] * floats_13[4] + floats_8[2] * floats_13[7];
			floats_9[2] = floats_8[0] * floats_13[2] + floats_8[1] * floats_13[5] + floats_8[2] * floats_13[8];
			floats_9[3] = floats_8[3] * floats_13[0] + floats_8[4] * floats_13[3] + floats_8[5] * floats_13[6];
			floats_9[4] = floats_8[3] * floats_13[1] + floats_8[4] * floats_13[4] + floats_8[5] * floats_13[7];
			floats_9[5] = floats_8[3] * floats_13[2] + floats_8[4] * floats_13[5] + floats_8[5] * floats_13[8];
			floats_9[6] = floats_8[6] * floats_13[0] + floats_8[7] * floats_13[3] + floats_8[8] * floats_13[6];
			floats_9[7] = floats_8[6] * floats_13[1] + floats_8[7] * floats_13[4] + floats_8[8] * floats_13[7];
			floats_9[8] = floats_8[6] * floats_13[2] + floats_8[7] * floats_13[5] + floats_8[8] * floats_13[8];
		}

		floats_9[0] *= f_5;
		floats_9[1] *= f_5;
		floats_9[2] *= f_5;
		floats_9[3] *= f_6;
		floats_9[4] *= f_6;
		floats_9[5] *= f_6;
		floats_9[6] *= f_7;
		floats_9[7] *= f_7;
		floats_9[8] *= f_7;
		return floats_9;
	}

	public abstract void ia(int var1, int var2, int var3);

	public void method11258(int id, AnimationFrameSet frameSet1, int frameIndex1, AnimationFrameSet frameSet2, int frameIndex2, int i_5, int duration, int i_8, boolean bool_9, int[] ints_10) {
		if (frameIndex1 == -1) return;
		method11260(); //waits until next frame is allowed?
		if (ea()) {
			AnimationFrame frame1 = frameSet1.frames[frameIndex1];
			AnimationFrameBase frameBase = frame1.frameBase;
			AnimationFrame frame2 = null;
			if (frameSet2 != null) {
				frame2 = frameSet2.frames[frameIndex2];
				if (frameBase != frame2.frameBase)
					frame2 = null;
			}
			method11266(id, frameBase, frame1, frame2, i_5, duration, 0, null, false, bool_9, i_8, ints_10);
			ka();
		}
		method11261(); //sets next frame allowable to be played?

	}

	public abstract int m();

	public abstract void method11259();

	public abstract void f(int var1);

	public abstract void ep(int var1, int var2, int var3);

	public abstract void t(int var1);

	public abstract void e(int var1, int[] var2, int var3, int var4, int var5, boolean var6, int var7, int[] var8);

	public abstract int dq();

	public abstract void wa();

	public abstract void resize(int var1, int var2, int var3);

	public abstract void df(short var1, short var2);

	public abstract boolean bh();

	public abstract void method11260();

	public abstract void method11261();

	public void method11262(int id, AnimationFrameSet frameSet, int frame1Index, AnimationFrameSet frameSet2, int frame2Index, int i_5, int i_6, int i_7, boolean bool_8) {
		if (frame1Index == -1) return;
		method11260();
		if (ea()) {
			AnimationFrame frame1 = frameSet.frames[frame1Index];
			AnimationFrameBase frameBase = frame1.frameBase;
			AnimationFrame frame2 = null;
			if (frameSet2 != null) {
				frame2 = frameSet2.frames[frame2Index];
				if (frameBase != frame2.frameBase)
					frame2 = null;
			}
			method11266(id, frameBase, frame1, frame2, i_5, i_6, i_7, null, false, bool_8, 65535, null);
			ka();
		}
		method11261();

	}

	public abstract void method11263(Matrix44Var var1, int var2, boolean var3);

	public void method11264(int id, AnimationFrameSet animationskeleton_1, int i_2, AnimationFrameSet animationskeleton_3, int i_4, int i_5, int i_6, AnimationFrameSet animationskeleton_7, int i_8, AnimationFrameSet animationskeleton_9, int i_10, int i_11, int i_12, boolean[] bools_13, boolean bool_14) {
		if (i_2 != -1) {
			if (bools_13 != null && i_8 != -1) {
				method11260();
				if (!ea()) {
					method11261();
				} else {
					AnimationFrame animationskin_15 = animationskeleton_1.frames[i_2];
					AnimationFrameBase animationskinnode_16 = animationskin_15.frameBase;
					AnimationFrame animationskin_17 = null;
					if (animationskeleton_3 != null) {
						animationskin_17 = animationskeleton_3.frames[i_4];
						if (animationskinnode_16 != animationskin_17.frameBase) {
							animationskin_17 = null;
						}
					}

					method11266(id, animationskinnode_16, animationskin_15, animationskin_17, i_5, i_6, 0, bools_13, false, bool_14, 65535, null);
					AnimationFrame animationskin_18 = animationskeleton_7.frames[i_8];
					AnimationFrame animationskin_19 = null;
					if (animationskeleton_9 != null) {
						animationskin_19 = animationskeleton_9.frames[i_10];
						if (animationskinnode_16 != animationskin_19.frameBase) {
							animationskin_19 = null;
						}
					}

					method11268(id, 0, i_2, new int[0], 0, 0, 0, 0, bool_14); //reset animation i assume before sending new one
					method11266(id, animationskin_18.frameBase, animationskin_18, animationskin_19, i_11, i_12, 0, bools_13, true, bool_14, 65535, null);
					ka();
					method11261();
				}
			} else {
				method11262(id, animationskeleton_1, i_2, animationskeleton_3, i_4, i_5, i_6, 0, bool_14);
			}
		}

	}

	public void method11266(int id, AnimationFrameBase animFrameBase, AnimationFrame animFrame1, AnimationFrame animFrame2, int i_4, int duration, int i_6, boolean[] bools_7, boolean bool_8, boolean bool_9, int modelIndex, int[] ints_11) {
		int frame1Index;
		if (animFrame2 != null && i_4 != 0) {
			frame1Index = 0;
			int frame2Index = 0;
			for (int frameIndex = 0; frameIndex < animFrameBase.count; frameIndex++) {
				boolean bool_15 = false;
				if (frame1Index < animFrame1.transformationCount && frameIndex == animFrame1.transformationIndices[frame1Index]) {
					bool_15 = true;
				}

				boolean bool_16 = false;
				if (frame2Index < animFrame2.transformationCount && frameIndex == animFrame2.transformationIndices[frame2Index]) {
					bool_16 = true;
				}

				if (bool_15 || bool_16) {
					if (bools_7 != null && bools_7[frameIndex] != bool_8 && animFrameBase.transformationTypes[frameIndex] != 0) {
						if (bool_15) {
							++frame1Index;
						}

						if (bool_16) {
							++frame2Index;
						}
					} else {
						short s_17 = 0;
						int type = animFrameBase.transformationTypes[frameIndex];
						if (type == 3 || type == 10)
							s_17 = 128; //zoom/size? usually what 128 is

						short frame1X;
						short frame1Y;
						short frame1Z;
						short frame1Skip;
						byte frame1Flag;
						if (bool_15) {
							frame1X = animFrame1.transformationX[frame1Index];
							frame1Y = animFrame1.transformationY[frame1Index];
							frame1Z = animFrame1.transformationZ[frame1Index];
							frame1Skip = animFrame1.skippedReferences[frame1Index];
							frame1Flag = animFrame1.transformationFlags[frame1Index];
							++frame1Index;
						} else {
							frame1X = s_17;
							frame1Y = s_17;
							frame1Z = s_17;
							frame1Skip = -1;
							frame1Flag = 0;
						}

						short frame2X;
						short frame2Y;
						short frame2Z;
						short frame2Skip;
						byte frame2Flag;
						if (bool_16) {
							frame2X = animFrame2.transformationX[frame2Index];
							frame2Y = animFrame2.transformationY[frame2Index];
							frame2Z = animFrame2.transformationZ[frame2Index];
							frame2Skip = animFrame2.skippedReferences[frame2Index];
							frame2Flag = animFrame2.transformationFlags[frame2Index];
							++frame2Index;
						} else {
							frame2X = s_17;
							frame2Y = s_17;
							frame2Z = s_17;
							frame2Skip = -1;
							frame2Flag = 0;
						}

						int i_29;
						int i_30;
						int i_31;
						if ((frame1Flag & 0x2) == 0 && (frame2Flag & 0x1) == 0) {
							int i_32;
							if (type == 2) {
								i_32 = frame2X - frame1X & 0x3fff;
								int i_33 = frame2Y - frame1Y & 0x3fff;
								int i_34 = frame2Z - frame1Z & 0x3fff;
								if (i_32 >= 8192) {
									i_32 -= 16384;
								}

								if (i_33 >= 8192) {
									i_33 -= 16384;
								}

								if (i_34 >= 8192) {
									i_34 -= 16384;
								}

								i_29 = frame1X + i_32 * i_4 / duration & 0x3fff;
								i_30 = frame1Y + i_33 * i_4 / duration & 0x3fff;
								i_31 = frame1Z + i_34 * i_4 / duration & 0x3fff;
							} else if (type == 9) {
								i_32 = frame2X - frame1X & 0x3fff;
								if (i_32 >= 8192) {
									i_32 -= 16384;
								}

								i_29 = frame1X + i_32 * i_4 / duration & 0x3fff;
								i_31 = 0;
								i_30 = 0;
							} else if (type == 7) {
								i_32 = frame2X - frame1X & 0x3f;
								if (i_32 >= 32) {
									i_32 -= 64;
								}

								i_29 = frame1X + i_32 * i_4 / duration & 0x3f;
								i_30 = frame1Y + (frame2Y - frame1Y) * i_4 / duration;
								i_31 = frame1Z + (frame2Z - frame1Z) * i_4 / duration;
							} else {
								i_29 = frame1X + (frame2X - frame1X) * i_4 / duration;
								i_30 = frame1Y + (frame2Y - frame1Y) * i_4 / duration;
								i_31 = frame1Z + (frame2Z - frame1Z) * i_4 / duration;
							}
						} else {
							i_29 = frame1X;
							i_30 = frame1Y;
							i_31 = frame1Z;
						}
						if (frame1Skip != -1) {
							beginTransformation(id, 0, animFrame1.id,  animFrameBase.labels[frame1Skip], 0, 0, 0, i_6, bool_9, modelIndex & animFrameBase.anIntArray7561[frame1Skip], ints_11);
						} else if (frame2Skip != -1) {
							beginTransformation(id, 0, animFrame1.id,  animFrameBase.labels[frame2Skip], 0, 0, 0, i_6, bool_9, modelIndex & animFrameBase.anIntArray7561[frame2Skip], ints_11);
						}
						beginTransformation(id, type, animFrame1.id,  animFrameBase.labels[frameIndex], i_29, i_30, i_31, i_6, bool_9, modelIndex & animFrameBase.anIntArray7561[frameIndex], ints_11);
					}
				}
			}
		} else {
			for (frame1Index = 0; frame1Index < animFrame1.transformationCount; frame1Index++) {
				short s_13 = animFrame1.transformationIndices[frame1Index];
				if (bools_7 == null || bools_7[s_13] == bool_8 || animFrameBase.transformationTypes[s_13] == 0) {
					short s_14 = animFrame1.skippedReferences[frame1Index];
					if (s_14 != -1) {
						beginTransformation(id, 0, animFrame1.id,  animFrameBase.labels[s_14], 0, 0, 0, i_6, bool_9, modelIndex & animFrameBase.anIntArray7561[s_14], ints_11);
					}

					beginTransformation(id, animFrameBase.transformationTypes[s_13], animFrame1.id,  animFrameBase.labels[s_13], animFrame1.transformationX[frame1Index], animFrame1.transformationY[frame1Index], animFrame1.transformationZ[frame1Index], i_6, bool_9, modelIndex & animFrameBase.anIntArray7561[s_13], ints_11);
				}
			}
		}
	}

	static boolean set = false;
	static int count = 0;

	public void beginTransformation(int id, int type, int frame, int[] labels, int transformX, int transformY, int transformZ, int i_6, boolean bool_7, int i_8, int[] ints_9) {
		int transformedX = transformX;
		int transformedZ = transformZ;
		int buffer;
		if (i_6 == 1) {
			if (type != 0 && type != 1) {
				if (type == 3) {
					buffer = transformedX;
					transformedX = transformedZ;
					transformedZ = buffer;
				} else if (type == 2) {
					buffer = transformedX;
					transformedX = -transformedZ & 0x3fff;
					transformedZ = buffer & 0x3fff;
				}
			} else {
				buffer = -transformedX;
				transformedX = transformedZ;
				transformedZ = buffer;
			}
		} else if (i_6 == 2) {
			if (type != 0 && type != 1) {
				if (type == 2) {
					transformedX = -transformedX & 0x3fff;
					transformedZ = -transformedZ & 0x3fff;
				}
			} else {
				transformedX = -transformedX;
				transformedZ = -transformedZ;
			}
		} else if (i_6 == 3) {
			if (type != 0 && type != 1) {
				if (type == 3) {
					buffer = transformedX;
					transformedX = transformedZ;
					transformedZ = buffer;
				} else if (type == 2) {
					buffer = transformedX;
					transformedX = transformedZ & 0x3fff;
					transformedZ = -buffer & 0x3fff;
				}
			} else {
				buffer = transformedX;
				transformedX = -transformedZ;
				transformedZ = buffer;
			}
		}

		if (i_8 != 65535) { //model index?
			e(type, labels, transformedX, transformY, transformedZ, bool_7, i_8, ints_9);
		} else {
			method11268(id, type, frame, labels, transformedX, transformY, transformedZ, i_6, bool_7);
		}

	}

	public abstract void bk(int var1);

	public abstract void method11268(int id, int type, int frame, int[] labels, int transformedX, int transformY, int transformedZ, int var6, boolean var7);

	public abstract void finish();

	public abstract void Q(int var1);

	public abstract void method11269(MeshRasterizer var1, int var2, int var3, int var4, boolean var5);

	public abstract void ka();

	public abstract void bo(int var1, int var2, int var3, int var4);

	public abstract int ct();

	public abstract boolean method11270(int var1, int var2, Matrix44Var var3, boolean var4, int var5);

	public abstract Shadow ga(Shadow var1);

	public abstract int N();

	public abstract int n();

	public abstract int RA();

	public abstract void bl(int var1, int[] var2, int var3, int var4, int var5, boolean var6, int var7, int[] var8);

	public abstract int YA();

	public abstract int dp();

	public abstract int AA();

	public abstract int ha();

	public abstract void KA(int var1);

	public abstract void bz(int var1);

	public abstract int Z();

	public abstract byte[] aw();

	public abstract boolean dh();

	public void method11271(int i_1, int i_2, int i_3, int i_4, int i_5, int i_6, float[] floats_7, int i_8, float f_9, float[] floats_10) {
		i_1 -= i_4;
		i_2 -= i_5;
		i_3 -= i_6;
		float f_11 = i_1 * floats_7[0] + i_2 * floats_7[1] + i_3 * floats_7[2];
		float f_12 = i_1 * floats_7[3] + i_2 * floats_7[4] + i_3 * floats_7[5];
		float f_13 = i_1 * floats_7[6] + i_2 * floats_7[7] + i_3 * floats_7[8];
		float f_14 = (float) Math.sqrt(f_11 * f_11 + f_12 * f_12 + f_13 * f_13);
		float f_15 = (float) Math.atan2(f_11, f_13) / 6.2831855F + 0.5F;
		float f_16 = (float) Math.asin(f_12 / f_14) / 3.1415927F + 0.5F + f_9;
		float f_17;
		if (i_8 == 1) {
			f_17 = f_15;
			f_15 = -f_16;
			f_16 = f_17;
		} else if (i_8 == 2) {
			f_15 = -f_15;
			f_16 = -f_16;
		} else if (i_8 == 3) {
			f_17 = f_15;
			f_15 = f_16;
			f_16 = -f_17;
		}

		floats_10[0] = f_15;
		floats_10[1] = f_16;
	}

	public abstract void retexture(short var1, short var2);

	public abstract void PA(int var1, int var2, int var3, int var4);

	public abstract boolean successful();

	public abstract boolean i();

	public abstract boolean u();

	public abstract void method11273(Matrix44Var var1);

	public abstract int cw();

	public abstract SurfaceSkin[] method11274();

	public abstract void method11275(int var1, int[] var2, int var3, int var4, int var5, int var6, boolean var7);

	public abstract void bj(int var1);

	public abstract MeshRasterizer method11276(byte var1, int var2, boolean var3);

	public abstract MeshRasterizer method11277(byte var1, int var2, boolean var3);

	public abstract MeshRasterizer method11278(byte var1, int var2, boolean var3);

	public abstract MeshRasterizer method11279(byte var1, int var2, boolean var3);

	public void method11281(Ground class390_1, int i_2, int i_3, int i_4, int i_5, int i_6, int i_7, int i_8) {
		boolean bool_9 = false;
		boolean bool_10 = false;
		boolean bool_11 = false;
		int i_12 = -i_5 / 2;
		int i_13 = -i_6 / 2;
		int i_14 = class390_1.averageHeight(i_12 + i_2, i_13 + i_4);
		int i_15 = i_5 / 2;
		int i_16 = -i_6 / 2;
		int i_17 = class390_1.averageHeight(i_15 + i_2, i_16 + i_4);
		int i_18 = -i_5 / 2;
		int i_19 = i_6 / 2;
		int i_20 = class390_1.averageHeight(i_18 + i_2, i_19 + i_4);
		int i_21 = i_5 / 2;
		int i_22 = i_6 / 2;
		int i_23 = class390_1.averageHeight(i_21 + i_2, i_22 + i_4);
		int i_24 = Math.min(i_14, i_17);
		int i_25 = Math.min(i_20, i_23);
		int i_26 = Math.min(i_17, i_23);
		int i_27 = Math.min(i_14, i_20);
		int i_28;
		int i_29;
		if (i_6 != 0) {
			i_28 = (int) (Math.atan2(i_24 - i_25, i_6) * 2607.5945876176133D) & 0x3fff;
			if (i_28 != 0) {
				if (i_7 != 0) {
					if (i_28 > 8192) {
						i_29 = 16384 - i_7;
						if (i_28 < i_29) {
							i_28 = i_29;
						}
					} else if (i_28 > i_7) {
						i_28 = i_7;
					}
				}

				t(i_28);
			}
		}

		if (i_5 != 0) {
			i_28 = (int) (Math.atan2(i_27 - i_26, i_5) * 2607.5945876176133D) & 0x3fff;
			if (i_28 != 0) {
				if (i_8 != 0) {
					if (i_28 > 8192) {
						i_29 = 16384 - i_8;
						if (i_28 < i_29) {
							i_28 = i_29;
						}
					} else if (i_28 > i_8) {
						i_28 = i_8;
					}
				}

				EA(i_28);
			}
		}

		i_28 = i_14 + i_23;
		if (i_17 + i_20 < i_28) {
			i_28 = i_17 + i_20;
		}

		i_28 = (i_28 >> 1) - i_3;
		if (i_28 != 0) {
			ia(0, i_28, 0);
		}

	}

	public abstract int ae();

	public abstract void aq(int var1);

	public abstract int cu();

	public abstract void method11282(Matrix44Var var1, EntityNode_Sub5 var2, int var3);

	public abstract void recolour(short var1, short var2);

	public abstract void bx(int var1);

	public abstract SurfaceSkin[] method11283();

	public abstract int ya();

	public abstract void bm(int var1);

	public abstract int ca();

	public abstract int c();

	public abstract void bf(int var1);

	public abstract void bn(int var1);

	public abstract void be();

	public void method11284(AnimationFrameSet animationskeleton_1, int i_2) {
		if (i_2 != -1) {
			method11260();
			if (!ea()) {
				method11261();
			} else {
				AnimationFrame animationskin_3 = animationskeleton_1.frames[i_2];
				AnimationFrameBase animationskinnode_4 = animationskin_3.frameBase;

				for (int i_5 = 0; i_5 < animationskin_3.transformationCount; i_5++) {
					short s_6 = animationskin_3.transformationIndices[i_5];
					if (animationskinnode_4.aBoolArray7563[s_6]) {
						if (animationskin_3.skippedReferences[i_5] != -1) {
							w(0, 0, 0, 0);
						}

						w(animationskinnode_4.transformationTypes[s_6], animationskin_3.transformationX[i_5], animationskin_3.transformationY[i_5], animationskin_3.transformationZ[i_5]);
					}
				}

				ka();
				method11261();
			}
		}

	}

	public abstract void bq();

	public abstract void w(int var1, int var2, int var3, int var4);

	public abstract void cr(int var1);

	public abstract void method11285();

	public abstract boolean method11286(int var1, int var2, Matrix44Var var3, boolean var4, int var5);

	public abstract boolean bv();

	public abstract boolean ea();

	public abstract void method11288();

	public abstract void bb(int var1, int var2, int var3, int var4);

	public abstract MeshRasterizer method11289(byte var1, int var2, boolean var3);

	public abstract int cc();

	public abstract void method11290(Matrix44Var var1, int var2, boolean var3);

	public abstract void method11291(Matrix44Var var1, int var2, boolean var3);

	public abstract void method11292(Matrix44Var var1, int var2, boolean var3);

	public abstract void method11293(Matrix44Var var1, EntityNode_Sub5 var2, int var3);

	public abstract int ar();

	public abstract void method11294(Matrix44Var var1);

	public abstract int cv();

	public abstract int cp();

	public abstract int dg();

	public abstract int ci();

	public abstract void bw(int var1);

	public abstract void method11295();

	public abstract int cb();

	public abstract MeshRasterizer method11296(byte var1, int var2, boolean var3);

	public abstract int cm();

	public abstract void bs(int var1);

	public abstract int ck();

	public abstract int co();

	public abstract void ce(int var1);

	public abstract void method11298(Matrix44Var var1, int var2, boolean var3);

	public abstract int ch();

	public abstract byte[] cz();

	public abstract void cq(short var1, short var2);

	public abstract void method11299(MeshRasterizer var1, int var2, int var3, int var4, boolean var5);

	public abstract void cy(int var1, int var2, int var3, int var4);

	public abstract ParticleEmitterConfig[] method11300();

	public abstract void p(int var1);

	public abstract void method11301();

	public abstract SurfaceSkin[] method11302();

	public abstract void dl(int var1);

	public abstract int dy();

	public abstract int dm();

	public void method11306(int i_1, int i_2, int i_3, int i_4, int i_5, int i_6, float[] floats_7, float f_8, int i_9, float f_10, float[] floats_11) {
		i_1 -= i_4;
		i_2 -= i_5;
		i_3 -= i_6;
		float f_12 = i_1 * floats_7[0] + i_2 * floats_7[1] + i_3 * floats_7[2];
		float f_13 = i_1 * floats_7[3] + i_2 * floats_7[4] + i_3 * floats_7[5];
		float f_14 = i_1 * floats_7[6] + i_2 * floats_7[7] + i_3 * floats_7[8];
		float f_15 = (float) Math.atan2(f_12, f_14) / 6.2831855F + 0.5F;
		if (f_8 != 1.0F) {
			f_15 *= f_8;
		}

		float f_16 = f_13 + 0.5F + f_10;
		float f_17;
		if (i_9 == 1) {
			f_17 = f_15;
			f_15 = -f_16;
			f_16 = f_17;
		} else if (i_9 == 2) {
			f_15 = -f_15;
			f_16 = -f_16;
		} else if (i_9 == 3) {
			f_17 = f_15;
			f_15 = f_16;
			f_16 = -f_17;
		}

		floats_11[0] = f_15;
		floats_11[1] = f_16;
	}

	public abstract int du();

	public abstract void dk(int var1, int var2, int var3);

	public abstract int dd();

	public abstract void di(short var1, short var2);

	public abstract void method11307(byte var1, byte[] var2);

	public abstract boolean dv();

	public abstract void method11308();

	public abstract void S(int var1);

	public abstract boolean method11309(int var1, int var2, Matrix44Var var3, boolean var4, int var5);

	public abstract void EA(int var1);

	public abstract Shadow dn(Shadow var1);

	public abstract Shadow da(Shadow var1);

	public abstract Shadow dw(Shadow var1);

	public abstract Shadow dr(Shadow var1);

	public abstract void bp();

	public abstract void method11312(byte var1, byte[] var2);

	public abstract int cd();

	public abstract void method11315(Matrix44Var var1);

	public abstract void by();

	public abstract void pa(int var1, int var2, Ground var3, Ground var4, int var5, int var6, int var7);

	public abstract void bi(int var1);

	public abstract SurfaceSkin[] method11331();

}
