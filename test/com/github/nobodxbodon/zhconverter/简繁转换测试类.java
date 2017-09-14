package com.github.nobodxbodon.zhconverter;

import static com.github.program_in_chinese.junit4_in_chinese.断言类.相等;

import org.junit.Test;

import com.github.nobodxbodon.zhconverter.简繁转换类.目标;

public class 简繁转换测试类 {

  @Test
  public void 静态方法测试() {
    确认简繁体互转("简单", "簡單");
    确认简繁体互转("曹操", "曹操");
    确认简繁体互转("赵云", "趙雲");
    确认简繁体互转("岳飞", "岳飛");

    // TODO: issue #1. 不知此字繁体是什么?
    确认简繁体互转("暰", "暰");
    
    // issue #4 简体转繁体时，“机械”一起的时候不能转换，但只有一个"机"字可以转换。
    确认简繁体互转("机", "機");
    确认简繁体互转("机械", "機械");

    // issue #5
    确认简繁体互转("一哄而散", "一鬨而散");
    
    // https://github.com/NLPchina/nlp-lang/issues/23
    确认简繁体互转("土著");
    确认简繁体互转("乾坤");
    
    // https://github.com/NLPchina/nlp-lang/issues/24
    确认简繁体互转("尼日利亚", "尼日利亞");
    确认简繁体互转("巴基斯坦");
    确认简繁体互转("厄瓜多尔", "厄瓜多爾");
    
    确认简繁体互转("有背光的机械式键盘", "有背光的機械式鍵盤");
    
    // https://github.com/nobodxbodon/zhconverter/issues/5
    确认简繁体互转("叶子", "葉子");
  }
  
  @Test
  public void 基本转换测试() {
    final 简繁转换类 繁体转换器 = 简繁转换类.取实例(目标.繁体);
    final 简繁转换类 简体转换器 = 简繁转换类.取实例(目标.简体);
    
    相等("簡單", 繁体转换器.转换("简单"));
    相等("简单", 简体转换器.转换("簡單"));
    
    // 如果已是简体, 简体转换后不变; 繁体亦然
    相等("簡單", 繁体转换器.转换("簡單"));
    相等("简单", 简体转换器.转换("简单"));
  }

  @Test
  public void 边界测试() {
    确认简繁体互转("", "");
    确认简繁体互转("a", "a");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void 异常测试_繁体() {
    简繁转换类.转换(null, 目标.繁体);
  }

  @Test(expected = IllegalArgumentException.class)
  public void 异常测试_简体() {
    简繁转换类.转换(null, 目标.简体);
  }
  
  private void 确认简繁体互转(String 文本) {
    确认简繁体互转(文本, 文本);
  }
  
  private void 确认简繁体互转(String 简体文本, String 繁体文本) {
    相等(繁体文本, 简繁转换类.转换(简体文本, 目标.繁体));
    相等(简体文本, 简繁转换类.转换(繁体文本, 目标.简体));
  }
}
