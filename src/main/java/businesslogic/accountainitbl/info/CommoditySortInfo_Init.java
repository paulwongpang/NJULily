package businesslogic.accountainitbl.info;

import java.util.ArrayList;

import po.CommoditySortPO;
import vo.CommoditySortVO;

public interface CommoditySortInfo_Init {
	
	/**
	 * 得到所有的商品分类PO
	 * @return
	 * @author Zing
	 * @version Dec 2, 2014 7:38:51 PM
	 */
	public ArrayList<CommoditySortPO> getSortPOs();
	
	/**
	 * 得到所有的商品分类VO
	 * @return
	 * @author Zing
	 * @version Dec 2, 2014 7:39:02 PM
	 */
	public ArrayList<CommoditySortVO> getSortVOs(ArrayList<CommoditySortPO> POs);
}
