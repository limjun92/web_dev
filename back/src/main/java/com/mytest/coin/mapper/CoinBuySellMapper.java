package com.mytest.coin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mytest.coin.dto.TradeInfo;

@Mapper
@Repository
public interface CoinBuySellMapper {
	int buyInfoInsert(TradeInfo tradeInfo);
	List<TradeInfo> selectTradeInfo();
	int UpdateBuyCoinInfo(Map<String, Object> map);
}
