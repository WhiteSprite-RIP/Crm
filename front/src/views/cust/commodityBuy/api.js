import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getCommodityBuyOne = (params) => {
    return getRequest('/commodityBuy/getOne', params)
}
export const getCommodityBuyList = (params) => {
    return getRequest('/commodityBuy/getByPage', params)
}
export const getCommodityBuyCount = (params) => {
    return getRequest('/commodityBuy/count', params)
}
export const addCommodityBuy = (params) => {
    return postRequest('/commodityBuy/insert', params)
}
export const editCommodityBuy = (params) => {
    return postRequest('/commodityBuy/update', params)
}
export const addOrEditCommodityBuy = (params) => {
    return postRequest('/commodityBuy/insertOrUpdate', params)
}
export const deleteCommodityBuy = (params) => {
    return postRequest('/commodityBuy/delByIds', params)
}
export const getCommodityList = (params) => {
    return getRequest('/commodity/getAll', params)
}