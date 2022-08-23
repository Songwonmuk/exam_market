package net.softsociety.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.exam.dao.MemberDAO;
import net.softsociety.exam.domain.Member;

@Service
@Slf4j
@Transactional
public class MemberSeviceImpl implements MemberService {

	@Autowired
    private MemberDAO memberDAO;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	
	@Override
	public int insertMember(Member member) {
		String encodedPassword = passwordEncoder.encode(member.getMemberpw());
		log.debug("암호화된 비번 {} : {}", member.getMemberpw(), encodedPassword);
		member.setMemberpw(encodedPassword);
		
		int result = memberDAO.insertMember(member);
		return result;
	}

   

}
