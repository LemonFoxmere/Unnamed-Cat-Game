package com.source.framework.util;

import com.source.excEnv.model.RectModel;

public class CollisionHandler {
	
	/*
	 * Collision side diagram:
	 * 
	 *       Side 0
	 *        _____
	 *       |  ^  |
	 *Side 1 |< O >| side 2
	 *       |__v__|
	 *       
	 *       Side 3
	 *       
	 * IE:
	 *          ____
	 *         |    |
	 *       __|_M1 |
	 *      |  |_|__|
	 *      | M2 |
	 *      |____|
	 *      
	 * the method collided will return a 2x4 matrix, indication the side of m1 collision, and m2 collision
	 * like so:
	 *    
	 * m1 collision sides, from 0-3, with 0 being untouched, and 1 being touched: [[0,1,0,1],
	 * m2 collision sides, from 0-3, with 0 being untouched, and 1 being touched:  [1,0,1,0]]
	 * 
	 * IE:
	 *     
	 *      ____
	 *     |    |
	 *     | M1 |
	 *    _|____|_
	 *   | |____| |
	 *   |   M2   |
	 *   |________|
	 *   
	 *   collision matrix:
	 *   [[0,1,1,1],
	 *    [1,0,0,0]]
	 */        
	
	public static boolean collided(RectModel m1, RectModel m2, float epsilon) {
    // consider x case
    float m1LeftX = m1.x;
    float m1RightX = m1.x + m1.w;
    float m2LeftX = m2.x;
    float m2RightX = m2.x + m2.w;

    if (!(m1LeftX > m2LeftX && m1LeftX < m2RightX) && !(m1RightX < m2RightX && m1RightX > m2LeftX)) return false;

    // consider y case
    float m1BottomY = m1.y + m1.h;
    float m2TopY = m2.y;
    float m2ThresholdY = m2.y + epsilon;
    if (m1BottomY >= m2TopY && m1BottomY <= m2ThresholdY) return true;
    return false;
  }
}
