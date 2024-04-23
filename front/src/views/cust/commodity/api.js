import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getCommodityOne = (params) => {
    return getRequest('/commodity/getOne', params)
}
export const getCommodityList = (params) => {
    return getRequest('/commodity/getByPage', params)
}
export const getCommodityCount = (params) => {
    return getRequest('/commodity/count', params)
}
export const addCommodity = (params) => {
    return postRequest('/commodity/insert', params)
}
export const editCommodity = (params) => {
    return postRequest('/commodity/update', params)
}
export const addOrEditCommodity = (params) => {
    return postRequest('/commodity/insertOrUpdate', params)
}
export const deleteCommodity = (params) => {
    return postRequest('/commodity/delByIds', params)
}