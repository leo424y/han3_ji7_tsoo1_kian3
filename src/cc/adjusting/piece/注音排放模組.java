package cc.adjusting.piece;

import java.awt.geom.Rectangle2D;

import cc.moveable_type.rectangular_area.分離活字;
import cc.moveable_type.rectangular_area.活字單元;

/**
 * 組合注音模組。一直佮注音活字擲入來，會排做一排。
 * 
 * @author Ihc
 */
abstract class 注音排放模組
{
	/** 進前加的活字 */
	protected 活字單元 累積活字;
	/** 上尾加的活字 */
	protected 活字單元 上尾活字;
	/** 用來予調整工具參考位置的活字 */
	protected 活字單元 對齊活字;

	/** 建立一个排注音的物件。 */
	注音排放模組()
	{
		累積活字 = new 分離活字();
		上尾活字 = new 分離活字();
		對齊活字 = new 分離活字();
	}

	/**
	 * 加入新的活字。
	 * 
	 * @param 新活字
	 *            欲新加的活字
	 */
	abstract void 加新的活字(活字單元 新活字);

	/**
	 * 提這馬攏總組合起來的活字。
	 * 
	 * @return 攏總組合起來的活字
	 */
	活字單元 目前結果()
	{
		活字單元 結果 = new 分離活字(累積活字);
		結果.合併活字(上尾活字);
		return 結果;
	}

	/**
	 * 提著上尾一个活字伊的歸屬範圍，毋是實際範圍。
	 * 
	 * @return 上尾一个活字伊的歸屬範圍
	 */
	Rectangle2D 上尾範圍()
	{
		return 上尾實際範圍();
	}

	/**
	 * 提著上尾一个活字伊的實際範圍。
	 * 
	 * @return 上尾一个活字伊的實際範圍
	 */
	Rectangle2D 上尾實際範圍()
	{
		return 上尾活字.字範圍();
	}

	/**
	 * 提著上尾一个活字伊的歸屬範圍，毋是實際範圍。
	 * 
	 * @return 上尾一个活字伊的歸屬範圍
	 */
	Rectangle2D 對齊範圍()
	{
		return 對齊活字.字範圍();
	}

	/**
	 * 判斷上尾加的活字有法度提來予組合工具對齊無。
	 * 
	 * @return 上尾加的活字有法度提來予組合工具對齊無
	 */
	protected boolean 上尾活字會當提來對齊無()
	{
		return 對齊活字.字範圍().getWidth() < 1e-8
				|| 上尾活字.字範圍().getWidth() * 3.0 >= 上尾活字.字範圍()
						.getHeight();
	}
}
