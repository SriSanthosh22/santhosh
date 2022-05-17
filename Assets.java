package EmployeeAssect.hibernate;

	import javax.persistence.Entity;
	import javax.persistence.Id;

	@Entity
	public class Assets {
		@Id
		Integer assetId;
		Integer serialNo;
		Integer printNo;
		String assetsType;
		Integer assetsPrice; 
		String assetsLocation;
		public Assets() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Integer getAssetsId() {
			return assetId;
		}
		public void setAssetsId(Integer assetsId) {
			this.assetId = assetsId;
		}
		public Integer getSerialNo() {
			return serialNo;
		}
		public void setSerialNo(Integer serialNo) {
			this.serialNo = serialNo;
		}
		public Integer getPrintNo() {
			return printNo;
		}
		public void setPrintNo(Integer printNo) {
			this.printNo = printNo;
		}
		public String getAssetsType() {
			return assetsType;
		}
		public void setAssetsType(String assestType) {
			this.assetsType = assestType;
		}
		public Integer getAssetsPrice() {
			return assetsPrice;
		}
		public void setAssetsPrice(Integer assestPrice) {
			this.assetsPrice = assestPrice;
		}
		public String getAssetsLocation() {
			return assetsLocation;
		}
		public void setAssetsLocation(String assestLocatio) {
			this.assetsLocation = assestLocatio;
		}
		@Override
		public String toString() {
			return "Assest [assetId=" + assetId + ", serialNo=" + serialNo + ", printNo=" + printNo + ", assetsType="
					+ assetsType + ", assetsPrice=" + assetsPrice + ", assetsLocation=" + assetsLocation + "]";
		}
		
		
		
		
	}
