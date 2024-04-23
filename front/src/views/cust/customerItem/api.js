import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getCustomerItemOne = (params) => {
    return getRequest('/customerItem/getOne', params)
}
export const getCustomerItemList = (params) => {
    return getRequest('/customerItem/getByPage', params)
}
export const getCustomerItemCount = (params) => {
    return getRequest('/customerItem/count', params)
}
export const addCustomerItem = (params) => {
    return postRequest('/customerItem/insert', params)
}
export const editCustomerItem = (params) => {
    return postRequest('/customerItem/update', params)
}
export const addOrEditCustomerItem = (params) => {
    return postRequest('/customerItem/insertOrUpdate', params)
}
export const deleteCustomerItem = (params) => {
    return postRequest('/customerItem/delByIds', params)
}
export const getCustomerList = (params) => {
    return getRequest('/customer/getAll', params)
}