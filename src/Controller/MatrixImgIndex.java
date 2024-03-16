package Controller;

import java.util.ArrayList;
import java.awt.Point;
import java.util.Random;
public class MatrixImgIndex {
	private int row,col;
	private int[][] matrix;
	
	public MatrixImgIndex(int row,int col) {
		this.row = row;
		this.col = col;
		this.matrix = new int[row][col];
		createMatrixControl();
	}
	private void createMatrixControl() {
		int maxAppear = 4;
		int imgCount = 21;
		int[] arrAppear= new int[imgCount+2];
		Random rd = new Random();
		ArrayList<Point> listPoint = new ArrayList<Point>();
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				listPoint.add(new Point(i,j));
			}
		}
		
		while(listPoint.size()>0) {
			int rdImg = rd.nextInt(imgCount)+1;
			if( arrAppear[rdImg] < maxAppear) {
				for(int j=0;j<2;j++) {
					int rdIndex = rd.nextInt(listPoint.size());
					int x = listPoint.get(rdIndex).x;
					int y = listPoint.get(rdIndex).y;
					matrix[x][y]= rdImg;
					arrAppear[rdImg]++;
					listPoint.remove(rdIndex);
					
				}
			}
			
		}
		
		
	}
	public void printMatrix() {
		for(int i = 0; i<this.row;i++) {
			for(int j=0;j<col;j++) {
				System.out.printf("%3d",matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	public boolean checkPoint(Point a, Point b) {
        if (!a.equals(b) && matrix[a.x][a.y] == matrix[b.x][b.y]) {
            return true;
        }
        return false;
    }
	
	public int getRow() {
		return row;
	}

	

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	

}
