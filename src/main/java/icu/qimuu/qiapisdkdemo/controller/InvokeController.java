package icu.qimuu.qiapisdkdemo.controller;

import com.qimuu.easyweb.common.BaseResponse;
import com.qimuu.easyweb.common.ResultUtils;
import com.qimuu.easyweb.exception.BusinessException;
import icu.qimuu.qiapisdk.client.QiApiClient;
import icu.qimuu.qiapisdk.exception.ApiException;
import icu.qimuu.qiapisdk.model.params.HoroscopeParams;
import icu.qimuu.qiapisdk.model.params.IpInfoParams;
import icu.qimuu.qiapisdk.model.params.RandomWallpaperParams;
import icu.qimuu.qiapisdk.model.params.WeatherParams;
import icu.qimuu.qiapisdk.model.request.HoroscopeRequest;
import icu.qimuu.qiapisdk.model.request.IpInfoRequest;
import icu.qimuu.qiapisdk.model.request.RandomWallpaperRequest;
import icu.qimuu.qiapisdk.model.request.WeatherRequest;
import icu.qimuu.qiapisdk.model.response.LoveResponse;
import icu.qimuu.qiapisdk.model.response.PoisonousChickenSoupResponse;
import icu.qimuu.qiapisdk.model.response.ResultResponse;
import icu.qimuu.qiapisdk.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: QiMu
 * @Date: 2023年09月17日 23:30
 * @Version: 1.0
 * @Description:
 */
@RequestMapping("/invoke")
@RestController
@Slf4j
public class InvokeController {
    @Resource
    private ApiService apiService;


    /**
     * 随机毒鸡汤
     *
     * @return {@link PoisonousChickenSoupResponse}
     */
    @GetMapping("/getPoisonousChickenSoup")
    public PoisonousChickenSoupResponse getPoisonousChickenSoup() {
        QiApiClient qiApiClient = new QiApiClient("7052a8594339a519e0ba5eb04a267a60", "d8d6df60ab209385a09ac796f1dfe3e1");
        PoisonousChickenSoupResponse poisonousChickenSoup = null;
        try {
            poisonousChickenSoup = apiService.getPoisonousChickenSoup(qiApiClient);
            System.out.println("poisonousChickenSoup = " + poisonousChickenSoup);
        } catch (ApiException e) {
            log.error(e.getMessage());
        }
        return poisonousChickenSoup;
    }


    /**
     * 随机毒鸡汤
     *
     * @return {@link PoisonousChickenSoupResponse}
     */
    @GetMapping("/getPoisonousChickenSoupKey")
    public PoisonousChickenSoupResponse getPoisonousChickenSoupKey(QiApiClient qiApiClient) {
        PoisonousChickenSoupResponse poisonousChickenSoup = null;
        try {
            poisonousChickenSoup = apiService.getPoisonousChickenSoup(qiApiClient);
            System.out.println("poisonousChickenSoup = " + poisonousChickenSoup);
        } catch (ApiException e) {
            log.error(e.getMessage());
        }
        return poisonousChickenSoup;
    }

    /**
     * 获取毒鸡汤未设置密钥
     *
     * @return {@link BaseResponse}<{@link PoisonousChickenSoupResponse}>
     */
    @GetMapping("/getPoisonousChickenSoupNotSetKey")
    public BaseResponse<PoisonousChickenSoupResponse> getPoisonousChickenSoupNotSetKey() {
        PoisonousChickenSoupResponse poisonousChickenSoup = null;
        try {
            poisonousChickenSoup = apiService.getPoisonousChickenSoup();
            System.out.println("poisonousChickenSoup = " + poisonousChickenSoup);
        } catch (ApiException e) {
            throw new com.qimuu.easyweb.exception.BusinessException(e.getCode(), e.getMessage());
        }
        return ResultUtils.success(poisonousChickenSoup);
    }

    /**
     * 随机情话
     *
     * @return {@link BaseResponse}<{@link LoveResponse}>
     */
    @GetMapping("/loveTalk/easyWeb")
    public BaseResponse<LoveResponse> getLoveTalkEasyWeb() {
        LoveResponse loveResponse;
        try {
            loveResponse = apiService.randomLoveTalk();
        } catch (ApiException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return ResultUtils.success(loveResponse);
    }

    @GetMapping("/loveTalk")
    public LoveResponse getLoveTalk() {
        LoveResponse loveResponse;
        try {
            loveResponse = apiService.randomLoveTalk();
        } catch (ApiException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return loveResponse;
    }

    @GetMapping("/loveTalk/setKey")
    public LoveResponse getLoveTalkSetKey() {
        LoveResponse loveResponse;
        try {
            QiApiClient qiApiClient = new QiApiClient("7052a8594339a519e0ba5eb04a267a60", "d8d6df60ab209385a09ac796f1dfe3e1");
            loveResponse = apiService.randomLoveTalk(qiApiClient);
        } catch (ApiException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return loveResponse;
    }

    /**
     * 获取星座运势
     *
     * @param horoscopeParams 星座参数
     * @return {@link BaseResponse}<{@link ResultResponse}>
     */
    @GetMapping("/getHoroscopeEasyWeb")
    public BaseResponse<ResultResponse> getHoroscopeEasyWeb(HoroscopeParams horoscopeParams) {
        ResultResponse horoscope;
        try {
            HoroscopeRequest horoscopeRequest = new HoroscopeRequest();
            horoscopeRequest.setRequestParams(horoscopeParams);
            horoscope = apiService.horoscope(horoscopeRequest);
        } catch (ApiException e) {
            throw new com.qimuu.easyweb.exception.BusinessException(e.getCode(), e.getMessage());
        }
        return ResultUtils.success(horoscope);
    }

    @GetMapping("/getHoroscope")
    public ResultResponse getHoroscope(HoroscopeParams horoscopeParams) {
        ResultResponse horoscope;
        try {
            HoroscopeRequest horoscopeRequest = new HoroscopeRequest();
            horoscopeRequest.setRequestParams(horoscopeParams);
            horoscope = apiService.horoscope(horoscopeRequest);
        } catch (ApiException e) {
            throw new com.qimuu.easyweb.exception.BusinessException(e.getCode(), e.getMessage());
        }
        return horoscope;
    }

    @GetMapping("/getHoroscope/setKey")
    public ResultResponse getHoroscopeSetKey(HoroscopeParams horoscopeParams) {
        ResultResponse horoscope;
        QiApiClient qiApiClient = new QiApiClient("7052a8594339a519e0ba5eb04a267a60", "d8d6df60ab209385a09ac796f1dfe3e1");
        try {
            HoroscopeRequest horoscopeRequest = new HoroscopeRequest();
            horoscopeRequest.setRequestParams(horoscopeParams);
            horoscope = apiService.horoscope(qiApiClient, horoscopeRequest);
        } catch (ApiException e) {
            throw new com.qimuu.easyweb.exception.BusinessException(e.getCode(), e.getMessage());
        }
        return horoscope;
    }

    /**
     * 获取ip信息
     * <p>
     * // * @param ipInfoParams ip信息参数
     *
     * @return {@link BaseResponse}<{@link ResultResponse}>
     */
    @GetMapping("/ipInfo/easyWeb")
    public BaseResponse<ResultResponse> getIpInfoEasyWeb(QiApiClient qiApiClient, IpInfoParams ipInfoParams) {
        ResultResponse resultResponse;
        try {
            IpInfoRequest ipInfoRequest = new IpInfoRequest();
            ipInfoRequest.setRequestParams(ipInfoParams);
            resultResponse = apiService.getIpInfo(qiApiClient, ipInfoRequest);
        } catch (ApiException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return ResultUtils.success(resultResponse);
    }

    @GetMapping("/ipInfo/toEasyWeb")
    public BaseResponse<ResultResponse> getIpInfoEasyWeb(IpInfoParams ipInfoParams) {
        ResultResponse resultResponse;
        try {
            IpInfoRequest ipInfoRequest = new IpInfoRequest();
            ipInfoRequest.setRequestParams(ipInfoParams);
            resultResponse = apiService.getIpInfo(ipInfoRequest);
        } catch (ApiException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return ResultUtils.success(resultResponse);
    }

    @GetMapping("/ipInfo")
    public ResultResponse getIpInfo(IpInfoParams ipInfoParams) {
        QiApiClient qiApiClient = new QiApiClient("7052a8594339a519e0ba5eb04a267a60", "d8d6df60ab209385a09ac796f1dfe3e1");
        ResultResponse resultResponse;
        try {
            IpInfoRequest ipInfoRequest = new IpInfoRequest();
            ipInfoRequest.setRequestParams(ipInfoParams);
            resultResponse = apiService.getIpInfo(qiApiClient, ipInfoRequest);
        } catch (ApiException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return resultResponse;
    }

    /**
     * 获取天气信息
     *
     * @param weatherParams ip信息参数
     * @return {@link BaseResponse}<{@link ResultResponse}>
     */
    @GetMapping("/weatherInfo/EasyWeb")
    public BaseResponse<ResultResponse> getWeatherInfoEasyWeb(WeatherParams weatherParams) {
        ResultResponse resultResponse;
        try {
            WeatherRequest weatherRequest = new WeatherRequest();
            weatherRequest.setRequestParams(weatherParams);
            resultResponse = apiService.getWeatherInfo(weatherRequest);
        } catch (ApiException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return ResultUtils.success(resultResponse);
    }

    @GetMapping("/weatherInfo")
    public ResultResponse getWeatherInfo(WeatherParams weatherParams) {
        ResultResponse resultResponse;
        try {
            WeatherRequest weatherRequest = new WeatherRequest();
            weatherRequest.setRequestParams(weatherParams);
            resultResponse = apiService.getWeatherInfo(weatherRequest);
        } catch (ApiException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return resultResponse;
    }

    @GetMapping("/weatherInfo/setKey")
    public ResultResponse getWeatherInfoSetKey(WeatherParams weatherParams) {
        ResultResponse resultResponse;
        QiApiClient qiApiClient = new QiApiClient("7052a8594339a519e0ba5eb04a267a60", "d8d6df60ab209385a09ac796f1dfe3e1");
        try {
            WeatherRequest weatherRequest = new WeatherRequest();
            weatherRequest.setRequestParams(weatherParams);
            resultResponse = apiService.getWeatherInfo(qiApiClient, weatherRequest);
        } catch (ApiException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return resultResponse;
    }

    @GetMapping("/randomWallpaper/setKey")
    public ResultResponse getRandomWallpaperSetKey(RandomWallpaperParams randomWallpaperParams) {
        ResultResponse resultResponse;
        QiApiClient qiApiClient = new QiApiClient("7052a8594339a519e0ba5eb04a267a60", "d8d6df60ab209385a09ac796f1dfe3e1");
        try {
            RandomWallpaperRequest randomWallpaperRequest = new RandomWallpaperRequest();
            randomWallpaperRequest.setRequestParams(randomWallpaperParams);
            resultResponse = apiService.getRandomWallpaper(qiApiClient, randomWallpaperRequest);
        } catch (ApiException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return resultResponse;
    }

    @GetMapping("/randomWallpaper")
    public ResultResponse getRandomWallpaper(RandomWallpaperParams randomWallpaperParams) {
        ResultResponse resultResponse;
        try {
            RandomWallpaperRequest randomWallpaperRequest = new RandomWallpaperRequest();
            randomWallpaperRequest.setRequestParams(randomWallpaperParams);
            resultResponse = apiService.getRandomWallpaper(randomWallpaperRequest);
        } catch (ApiException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return resultResponse;
    }

    @GetMapping("/randomWallpaper/easyWeb")
    public BaseResponse<ResultResponse> getRandomWallpaperEasyWeb(RandomWallpaperParams randomWallpaperParams) {
        ResultResponse resultResponse;
        try {
            RandomWallpaperRequest randomWallpaperRequest = new RandomWallpaperRequest();
            randomWallpaperRequest.setRequestParams(randomWallpaperParams);
            resultResponse = apiService.getRandomWallpaper(randomWallpaperRequest);
        } catch (ApiException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return ResultUtils.success(resultResponse);
    }
}
