import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getCustomerOrderOne = (params) => {
    return getRequest('/customerOrder/getOne', params)
}
export const getCustomerOrderList = (params) => {
    return getRequest('/customerOrder/getByPage', params)
}
export const getCustomerOrderCount = (params) => {
    return getRequest('/customerOrder/count', params)
}
export const addCustomerOrder = (params) => {
    return postRequest('/customerOrder/insert', params)
}
export const editCustomerOrder = (params) => {
    return postRequest('/customerOrder/update', params)
}
export const addOrEditCustomerOrder = (params) => {
    return postRequest('/customerOrder/insertOrUpdate', params)
}
export const deleteCustomerOrder = (params) => {
    return postRequest('/customerOrder/delByIds', params)
}
export const getCommodityList = (params) => {
    return getRequest('/commodity/getAll', params)
}
export const getCustomerList = (params) => {
    return getRequest('/customer/getAll', params)
}