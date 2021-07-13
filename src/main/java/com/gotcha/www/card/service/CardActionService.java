package com.gotcha.www.card.service;

import java.util.List;

import com.gotcha.www.card.vo.CardActDTO;
import com.gotcha.www.workList.vo.CardVO;

public interface CardActionService {
	int getMaxPosition(CardVO cardVO);
	void updateCardMove(CardVO cardVO);
	void updateDestPosition(CardVO cardVO);
	void updateNowPosition(CardVO cardVO);

}
