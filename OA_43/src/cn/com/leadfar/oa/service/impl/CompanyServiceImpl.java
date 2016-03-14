package cn.com.leadfar.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.leadfar.oa.dao.CompanyDao;
import cn.com.leadfar.oa.model.Company;
import cn.com.leadfar.oa.model.Party;
import cn.com.leadfar.oa.service.CompanyService;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
	@Resource
	private CompanyDao companyDao;
	private Company company;

	@Override
	public Company getCompany(int companyId) {
		if (companyId == 0) {// 则为总公司,分公司都会传id
			List<Company> companies = companyDao.getAll(Company.class);
			for (Company company : companies) {
				if (company.getParent() == null) {// 则为总公司
					return company;//当总公司存在时,将查询到的传递出去，不存在时，最后return的company为空,在action中判断
				}
			}
		} else {// 为分公司时，或为有id的总公司时
			company = companyDao.getById(Company.class, companyId);
		}

		return company;
	}

	@Override
	public void save(Party company) {
		if(company.getId()==null){//为0则为保存
			companyDao.save(company);
		}else{
			companyDao.update(company);//注意，这只是从页面提交的数据，不能直接更新这个company对象，
			
			//他的关联关系会消失，得根据id取得party对象，将party的parent，children，传递到从页面传递过来的company中
		}
		
	}

	@Override
	public void update(Company company) {
		companyDao.update(company);
		
	}
}
