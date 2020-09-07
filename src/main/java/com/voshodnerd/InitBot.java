package com.voshodnerd;
import com.voshodnerd.repository.GoodsRepository;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Configuration
public class InitBot {

    private static String PROXY_HOST = "172.16.10.3" /* proxy host */;
    private static Integer PROXY_PORT = 3128 /* proxy port */;
    private  static String TOKEN="1185831583:AAGTCscweoCNV8nzQ-NtmXflBi0eqjGuHSE";
    private static  String BOT_USERNAME="KnowUzbekBot";

    @Autowired
    GoodsRepository repository;


    @Bean
  public  Bot  GetBotBean() {
      Bot bot=null;
      try {
          ApiContextInitializer.init();
          // Create the TelegramBotsApi object to register your bots
          TelegramBotsApi botsApi = new TelegramBotsApi();
          // Set up Http proxy
          DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
          HttpHost httpHost = new HttpHost(PROXY_HOST, PROXY_PORT);
          RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).setAuthenticationEnabled(false).build();
          botOptions.setRequestConfig(requestConfig);
          botOptions.setHttpProxy(httpHost);
          // Register your newly created AbilityBot
           bot = new Bot(BOT_USERNAME,TOKEN,botOptions);
          botsApi.registerBot(bot);
          bot.setGoodsRepository(repository);


      } catch (TelegramApiException e) {
          e.printStackTrace();
      }
      return  bot;

  }

}
