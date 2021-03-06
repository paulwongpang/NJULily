package businesslogic.purchasebl.info;

import java.rmi.RemoteException;

import dataenum.BillType;

public interface CommodityInfo_Purchase {

	/**
	 * @param ID
	 * @return
	 * @author Zing
	 * @version Dec 10, 2014 9:45:06 AM
	 * @throws RemoteException
	 */
	public String getType(String ID) throws RemoteException;

	/**
	 * @param ID
	 * @return
	 * @author Zing
	 * @version Dec 10, 2014 9:45:03 AM
	 * @throws RemoteException
	 */
	public String getName(String ID) throws RemoteException;

	/**
	 * @param id
	 * @param number
	 * @param price
	 * @param type
	 * @author Zing
	 * @version Dec 10, 2014 9:45:01 AM
	 * @throws RemoteException
	 */
	public void changeCommodityInfo(String id, int number, double price, BillType type) throws RemoteException;

	/**
	 * 是否可以删除
	 * 只要创建了进货单，就不可以被删除
	 * @param iD
	 * @param b
	 * @author Zing
	 * @version Dec 10, 2014 9:44:35 AM
	 * @throws RemoteException
	 */
	public void setDelete(String ID, boolean b) throws RemoteException;

	/**
	 * 判断商品数量是否小于进货退货数量
	 * @param ID
	 * @param number
	 * @return
	 * @author Zing
	 * @version Dec 13, 2014 10:44:03 AM
	 * @throws RemoteException
	 */
	public boolean checkNumber(String ID, int number) throws RemoteException;
}
