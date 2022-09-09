package cn.edu.njupt;

public class Triangle {
	protected long lborderA=0;
	protected long lborderB=0;
	protected long lborderC=0;
	public Triangle(long lborderA,long lborderB,long lborderC){
		this.lborderA=lborderA;
		this.lborderB=lborderB;
		this.lborderC=lborderC;
	}
	public static boolean isTriangle(Triangle triangle){
		boolean isTriangle=false;
		if((triangle.lborderA>0&&triangle.lborderA<=Long.MAX_VALUE/2)
				&&(triangle.lborderB>0&&triangle.lborderB<=Long.MAX_VALUE/2)
				&&(triangle.lborderB>0&&triangle.lborderB<=Long.MAX_VALUE/2))
		{if((triangle.lborderA<(triangle.lborderB+triangle.lborderC))
				&&(triangle.lborderB<(triangle.lborderA+triangle.lborderC))
				&&(triangle.lborderC<(triangle.lborderA+triangle.lborderB)))
		{isTriangle=true;}
		}
		return isTriangle;
}
	public static String getType(Triangle triangle){
		String strType="不是三角形";
		if(isTriangle(triangle)){
			if(triangle.lborderA==triangle.lborderB
					&&triangle.lborderB==triangle.lborderC){
			strType="等边三角形";
			}else if((triangle.lborderA!=triangle.lborderB)
					&&(triangle.lborderB!=triangle.lborderC)
					&&(triangle.lborderA!=triangle.lborderC)){
						strType="不等边三角形";
					}
					else{
						strType="等腰三角形";
					}
			}
		return strType;
	}
	
}
