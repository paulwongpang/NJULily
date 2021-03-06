package vo.commodity;

import vo.ValueObject;

/**
 * 商品值对象
 * @author cylong
 * @version Oct 26, 2014 2:26:31 PM
 */
public class CommodityVO extends ValueObject {

	/** 商品名称 */
	public String name;
	/** 商品型号 */
	public String type;
	/** 商品分类ID */
	public String sortID;
	/** 商品库存数量 */
	public int inventoryNum;
	/** 商品进价 */
	public double purPrice;
	/** 商品售价 */
	public double salePrice;
	/** 商品最近进价 */
	public double recentPurPrice;
	/** 商品最近售价 */
	public double recentSalePrice;
	/** 商品警戒数量 */
	public int alarmNumber;

	/** 每次销售后计算平均售价 */
	public double aveSale;
	/** 每次进货后计算平均进价 */
	public double avePur;

	public CommodityVO(String ID, String name, String type, String sortID, int inventoryNum, double purPrice, double salePrice, double recentPurpPrice, double recentSalePrice, int alarmNumber) {
		this.ID = ID;
		this.name = name;
		this.type = type;
		this.sortID = sortID;
		this.inventoryNum = inventoryNum;
		this.purPrice = purPrice;
		this.salePrice = salePrice;
		this.recentPurPrice = recentPurpPrice;
		this.recentSalePrice = recentSalePrice;
		this.alarmNumber = alarmNumber;
	}

	/**
	 * 期初建账的商品条目
	 * @param ID
	 * @param name
	 * @param type
	 * @param sortID
	 * @param aveSale
	 * @param avePur
	 */
	public CommodityVO(String ID, String name, String type, String sortID, double aveSale, double avePur) {
		this.ID = ID;
		this.name = name;
		this.type = type;
		this.sortID = sortID;
		this.aveSale = aveSale;
		this.avePur = avePur;
	}

}
