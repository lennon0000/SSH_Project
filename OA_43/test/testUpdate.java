import java.util.Random;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.leadfar.oa.model.Company;
import cn.com.leadfar.oa.model.Dept;
import cn.com.leadfar.oa.model.Party;
import cn.com.leadfar.oa.model.Person;
import cn.com.leadfar.oa.model.Position;
import cn.com.leadfar.oa.service.CompanyService;
import cn.com.leadfar.oa.service.PartyService;

public class testUpdate extends TestCase {
	public void testUpdate01() {
		BeanFactory factory = new ClassPathXmlApplicationContext(
				"applicationContext-*.xml");
		PartyService ps = (PartyService) factory.getBean("partyService");
		CompanyService cs = (CompanyService) factory.getBean("companyService");
		Company party = new Company();
		party.setId(1);

		for (int i = 0; i < 5; i++) {
			Dept d = new Dept();
			d.setName("部门——" + i);
			d.setParent(party);
			ps.save(d);
			for (int j = 0; j < 2; j++) {
				Position p = new Position();
				p.setParent(d);
				p.setName("岗位——" + j);
				ps.save(p);
				for (int k = 0; k < 3; k++) {
					Person per = new Person();
					per.setParent(p);
					per.setName("人员——" + k);
					ps.save(per);
				}
			}
		}
		// ps.update(party);
		// cs.save(party);
		ps.save(party);
	}

	public void testAddPerson() {
		BeanFactory factory = new ClassPathXmlApplicationContext(
				"applicationContext-*.xml");
		PartyService ps = (PartyService) factory.getBean("partyService");

		Position p = new Position();
		p.setId(3);
		for (int i = 0; i < 200; i++) {
			Person per = new Person();
			per.setParent(p);
			per.setName("人员——" + i);
			per.setCode(i);
			per.setPhoneNumber(new Random().nextInt(99999));
			per.setSex("男");
			ps.save(per);
		}

	}
	public void testUpcase(){
		String s = ("Tff");
		System.out.println(s.toUpperCase());
	}

}
