package icu.qimuu.qiapisdkdemo.controller;

import com.qimuu.easyweb.common.BaseResponse;
import com.qimuu.easyweb.common.ErrorCode;
import com.qimuu.easyweb.common.ResultUtils;
import com.qimuu.easyweb.exception.BusinessException;
import icu.qimuu.qiapisdk.client.QiApiClient;
import icu.qimuu.qiapisdk.exception.ApiException;
import icu.qimuu.qiapisdk.model.params.HoroscopeParams;
import icu.qimuu.qiapisdk.model.params.IpInfoParams;
import icu.qimuu.qiapisdk.model.params.NameParams;
import icu.qimuu.qiapisdk.model.params.WeatherParams;
import icu.qimuu.qiapisdk.model.request.*;
import icu.qimuu.qiapisdk.model.response.LoveResponse;
import icu.qimuu.qiapisdk.model.response.NameResponse;
import icu.qimuu.qiapisdk.model.response.PoisonousChickenSoupResponse;
import icu.qimuu.qiapisdk.model.response.ResultResponse;
import icu.qimuu.qiapisdk.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
     * get name get
     *
     * @param name 名称
     * @return {@link BaseResponse}<{@link NameResponse}>
     */
    @GetMapping("/getName/get")
    public BaseResponse<NameResponse> getNameGet(String name) {
        QiApiClient qiApiClient = new QiApiClient("7052a8594339a519e0ba5eb04a267a60", "d8d6df60ab209385a09ac796f1dfe3e1");
        NameResponse nameResponse = null;
        try {
            NameParams nameParams = new NameParams().setName(name);
            nameParams.setName(name);
            NameRequest nameRequest = new NameRequest();
            nameRequest.setRequestParams(nameParams);
            nameResponse = apiService.request(qiApiClient, nameRequest);
        } catch (ApiException e) {
            throw new com.qimuu.easyweb.exception.BusinessException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
        return ResultUtils.success(nameResponse);
    }

    /**
     * 获取姓名
     *
     * @param nameParams 名称参数
     * @return {@link BaseResponse}<{@link NameResponse}>
     */
    @PostMapping("/getName/post")
    public BaseResponse<NameResponse> getNamePost(@RequestBody NameParams nameParams) {
        QiApiClient qiApiClient = new QiApiClient("7052a8594339a519e0ba5eb04a267a60", "d8d6df60ab209385a09ac796f1dfe3e1");
        NameResponse nameResponse = null;
        try {
            NameRequest nameRequest = new NameRequest();
            nameRequest.setRequestParams(nameParams);
            nameResponse = apiService.request(qiApiClient, nameRequest);
        } catch (ApiException e) {
            throw new com.qimuu.easyweb.exception.BusinessException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
        System.err.println(nameResponse);
        return ResultUtils.success(nameResponse);
    }

    /**
     * 基本请求
     *
     * @param currencyRequest 货币请求
     * @return {@link BaseResponse}<{@link Object}>
     */
    @PostMapping("/base")
    public BaseResponse<Object> baseRequestNotKey(@RequestBody CurrencyRequest currencyRequest) {
        ResultResponse request = null;
        try {
            request = apiService.request(currencyRequest);
        } catch (ApiException e) {
            throw new com.qimuu.easyweb.exception.BusinessException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
        System.err.println(request);
        return ResultUtils.success(request.getData());
    }

    /**
     * 基本请求
     *
     * @param currencyRequest 货币请求
     * @return {@link BaseResponse}<{@link Object}>
     */
    @PostMapping("/key")
    public BaseResponse<Object> baseRequest(@RequestBody CurrencyRequest currencyRequest) {
        QiApiClient qiApiClient = new QiApiClient("7052a8594339a519e0ba5eb04a267a60", "d8d6df60ab209385a09ac796f1dfe3e1");
        ResultResponse request = null;
        try {
            request = apiService.request(qiApiClient, currencyRequest);
        } catch (ApiException e) {
            throw new com.qimuu.easyweb.exception.BusinessException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
        System.err.println(request);
        return ResultUtils.success(request.getData());
    }

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
    @GetMapping("/love")
    public BaseResponse<LoveResponse> getLoveResponse() {
        LoveResponse loveResponse;
        try {
            loveResponse = apiService.randomLoveTalk();
        } catch (ApiException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
        return ResultUtils.success(loveResponse);
    }

    /**
     * 获取星座运势
     *
     * @param horoscopeParams 星座参数
     * @return {@link BaseResponse}<{@link ResultResponse}>
     */
    @GetMapping("/horoscope")
    public BaseResponse<ResultResponse> getHoroscope(HoroscopeParams horoscopeParams) {
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

    /**
     * 获取ip信息
     *
     * @param ipInfoParams ip信息参数
     * @return {@link BaseResponse}<{@link ResultResponse}>
     */
    @GetMapping("/ipInfo")
    public BaseResponse<ResultResponse> getIpInfo(IpInfoParams ipInfoParams) {
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

    /**
     * 获取天气信息
     *
     * @param weatherParams ip信息参数
     * @return {@link BaseResponse}<{@link ResultResponse}>
     */
    @GetMapping("/weatherInfo")
    public BaseResponse<ResultResponse> getWeatherInfo(WeatherParams weatherParams) {
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
}
