import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getCustomerOne = (params) => {
    return getRequest('/customer/getOne', params)
}
export const getCustomerList = (params) => {
    return getRequest('/customer/getByPage', params)
}
export const getCustomerCount = (params) => {
    return getRequest('/customer/count', params)
}
export const addCustomer = (params) => {
    return postRequest('/customer/insert', params)
}
export const editCustomer = (params) => {
    return postRequest('/customer/update', params)
}
export const addOrEditCustomer = (params) => {
    return postRequest('/customer/insertOrUpdate', params)
}
export const deleteCustomer = (params) => {
    return postRequest('/customer/delByIds', params)
}