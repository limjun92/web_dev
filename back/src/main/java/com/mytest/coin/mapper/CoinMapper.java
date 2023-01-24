package com.mytest.coin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mytest.coin.dto.CoinAllInfoDto;
import com.mytest.coin.dto.CoinKeyDto;

@Mapper
@Repository
public interface CoinMapper {
	CoinKeyDto getCoinKey(int user_id);
	List<CoinAllInfoDto> getCoinAllInfo(int user_id);
	int coinInfoUpdate(CoinAllInfoDto coinInfoUpdate);
}
